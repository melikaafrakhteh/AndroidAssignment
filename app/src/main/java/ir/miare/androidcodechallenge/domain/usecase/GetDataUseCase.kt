package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.domain.repository.RankingRepository
import ir.miare.androidcodechallenge.util.Either
import javax.inject.Inject

class GetDataUseCase @Inject constructor(
    private val repository: RankingRepository
) {
    suspend operator fun invoke(): Either<List<RankingModel>> {
        return repository.getData()
    }
}