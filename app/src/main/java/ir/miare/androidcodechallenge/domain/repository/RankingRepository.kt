package ir.miare.androidcodechallenge.domain.repository

import ir.miare.androidcodechallenge.data.model.FakeData
import kotlinx.coroutines.flow.Flow

interface RankingRepository {

    fun getData(): Flow<List<FakeData>>

}