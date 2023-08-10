package ir.miare.androidcodechallenge.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.androidcodechallenge.data.mapper.DataDomainMapper
import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.data.reporitory.RankingRepositoryImpl
import ir.miare.androidcodechallenge.domain.model.RankingModel
import ir.miare.androidcodechallenge.domain.repository.RankingRepository
import ir.miare.androidcodechallenge.util.Mapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindRankingRepository(
        repository: RankingRepositoryImpl
    ): RankingRepository

    @Singleton
    @Binds
    fun bindDataDomainMapper(
        mapper: DataDomainMapper
    ): Mapper<FakeData, RankingModel>

}