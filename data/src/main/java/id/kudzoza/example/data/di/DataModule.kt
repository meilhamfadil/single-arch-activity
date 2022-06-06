package id.kudzoza.example.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kudzoza.example.data.domain.MovieUseCase
import id.kudzoza.example.data.repository.MovieRepository
import id.kudzoza.example.data.repository.source.cache.dao.MovieDao
import id.kudzoza.example.data.repository.source.cache.entity.MovieEntityMapper
import id.kudzoza.example.data.repository.source.network.payload.MoviePayloadMapper
import javax.inject.Singleton

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieUseCase(
        movieRepository: MovieRepository,
        movieDao: MovieDao,
        moviePayloadMapper: MoviePayloadMapper,
        movieEntityMapper: MovieEntityMapper,
    ): MovieUseCase {
        return MovieUseCase(
            movieRepository,
            movieDao,
            moviePayloadMapper,
            movieEntityMapper
        )
    }

}