package ir.miare.androidcodechallenge.data.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.androidcodechallenge.data.remote.RankingApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Reusable
    @Provides
    fun provideRankingApiService(
        retrofit: Retrofit
    ): RankingApi {
        return retrofit.create(RankingApi::class.java)
    }

}