package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.data.mapper.DataDomainMapper
import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.domain.repository.RankingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repository: RankingRepository,
    private val dataDomainMapper: DataDomainMapper,
) {
    operator fun invoke(): Flow<List<RankingModel>> {
        return repository.getData().map { items ->
            items.map { fakeData ->
                dataDomainMapper.convertFirstObjectToSecond(fakeData)
            }
        }
    }

}