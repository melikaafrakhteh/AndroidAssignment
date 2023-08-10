package ir.miare.androidcodechallenge.data.reporitory

import ir.miare.androidcodechallenge.data.mapper.DataDomainMapper
import ir.miare.androidcodechallenge.data.remote.RankingApi
import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.domain.repository.RankingRepository
import ir.miare.androidcodechallenge.util.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RankingRepositoryImpl @Inject constructor(
    private val api: RankingApi,
    private val dataDomainMapper: DataDomainMapper,
) : RankingRepository {

    override suspend fun getData(): Either<List<RankingModel>> {
        return when (val result = api.getData()) {
            is Either.Failure -> Either.Failure(result.error)
            is Either.Success -> Either.Success(result.value.map { fakeData ->
                dataDomainMapper.convertFirstObjectToSecond(fakeData)
            })
        }
    }

}