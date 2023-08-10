package ir.miare.androidcodechallenge.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.miare.androidcodechallenge.data.mapper.DataDomainMapper
import ir.miare.androidcodechallenge.domain.repository.RankingRepository
import ir.miare.androidcodechallenge.domain.usecase.GetDataUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideGetDataUseCase(
        repository: RankingRepository,
        mapper: DataDomainMapper
    ): GetDataUseCase {
        return GetDataUseCase(repository, mapper)
    }

}