package ir.miare.androidcodechallenge.domain.repository

import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.util.Either

interface RankingRepository {

    suspend fun getData(): Either<List<RankingModel>>

}