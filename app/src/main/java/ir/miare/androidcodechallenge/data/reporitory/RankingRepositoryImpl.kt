package ir.miare.androidcodechallenge.data.reporitory

import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.data.remote.RankingApi
import ir.miare.androidcodechallenge.domain.repository.RankingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RankingRepositoryImpl @Inject constructor(
    private val api: RankingApi,
) : RankingRepository {

    override fun getData(): Flow<List<FakeData>> = flow {
        val data = api.getData()
        emit(data)
    }

}