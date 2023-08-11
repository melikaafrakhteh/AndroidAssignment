package ir.miare.androidcodechallenge.presentation.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.domain.model.Sorting
import ir.miare.androidcodechallenge.domain.model.toItemModels
import ir.miare.androidcodechallenge.domain.usecase.GetDataUseCase
import ir.miare.androidcodechallenge.util.ViewResource
import ir.miare.androidcodechallenge.util.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val useCase: GetDataUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RankingState())
    var state: StateFlow<RankingState> = _state.asStateFlow()
    private var unsortedData = listOf<RankingModel>()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            useCase.invoke()
                .asResult()
                .collect { result ->
                    when (result) {
                        is ViewResource.Loading -> {
                            _state.value =
                                _state.value.copy(data = ViewResource.Loading())
                        }
                        is ViewResource.Error -> {
                            _state.value =
                                _state.value.copy(data = ViewResource.Error(result.error))
                        }

                        is ViewResource.Success -> {
                            unsortedData = result.data
                            _state.value =
                                _state.value.copy(data = ViewResource.Success(result.data))
                            changeSortingByType(Sorting.None)
                        }
                        ViewResource.NotAvailable -> Unit
                    }
                }
        }
    }

    internal fun changeSortingByType(type: Sorting) {
        when (type) {
            Sorting.TeamAndLeagueRanking -> {
                val data = state.value.data
                if (data is ViewResource.Success) {
                    val sortedRankings = unsortedData
                        .map { ranking ->
                            val sortedPlayers =
                                ranking.players.sortedBy { player -> player.team.rank }
                            RankingModel(ranking.league, sortedPlayers)
                        }.sortedBy { ranking ->
                            ranking.league.rank
                        }

                    _state.update {
                        it.copy(data = ViewResource.Success(sortedRankings.toItemModels()))
                    }
                }
            }

            Sorting.PlayerMostGoal -> {
                val data = state.value.data
                if (data is ViewResource.Success) {
                    val sortedRankings = unsortedData
                        .map { ranking ->
                            ranking.players
                        }
                        .flatten()
                        .sortedByDescending { player ->
                            player.totalGoal
                        }

                    _state.update {
                        it.copy(data = ViewResource.Success(sortedRankings))
                    }
                }
            }
            Sorting.AverageGoal -> {
                val data = state.value.data
                if (data is ViewResource.Success) {
                    val sortedRankings = unsortedData
                        .sortedByDescending { ranking ->
                            ranking.players.sumOf { player -> player.totalGoal } / ranking.league.totalMatches.toFloat()
                        }

                    _state.update {
                        it.copy(data = ViewResource.Success(sortedRankings.toItemModels()))
                    }
                }
            }
            Sorting.None -> {
                if (state.value.data is ViewResource.Success) {
                    _state.update {
                        _state.value.copy(data = ViewResource.Success(unsortedData.toItemModels()))
                    }
                }
            }
        }
    }

}