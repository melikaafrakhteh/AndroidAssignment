package ir.miare.androidcodechallenge.data.remote

import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.util.Either
import retrofit2.http.GET

interface RankingApi {

    @Mock("data.json")
    @GET("list")
    suspend fun getData(): Either<List<FakeData>>

}