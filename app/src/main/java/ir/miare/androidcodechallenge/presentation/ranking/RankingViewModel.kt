package ir.miare.androidcodechallenge.presentation.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.miare.androidcodechallenge.domain.usecase.GetDataUseCase
import ir.miare.androidcodechallenge.util.Either
import ir.miare.androidcodechallenge.util.ViewResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val useCase: GetDataUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RankingState())
    var state: StateFlow<RankingState> = _state.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        _state.value = _state.value.copy(data = ViewResource.Loading())
        viewModelScope.launch {
            when (val result = useCase()) {
                is Either.Failure ->
                    _state.value =
                        _state.value.copy(data = ViewResource.Error(result.error.toString()))
                is Either.Success ->
                    _state.value = _state.value.copy(data = ViewResource.Success(result.value))
            }
        }
    }

}