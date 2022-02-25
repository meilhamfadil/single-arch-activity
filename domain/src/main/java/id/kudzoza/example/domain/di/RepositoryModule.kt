package id.kudzoza.example.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kudzoza.example.domain.repository.MovieRepository
import id.kudzoza.example.domain.repository.MovieRepositoryImpl
import id.kudzoza.example.domain.service.MovieService
import javax.inject.Singleton

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepo(
        movieService: MovieService,
    ): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

}