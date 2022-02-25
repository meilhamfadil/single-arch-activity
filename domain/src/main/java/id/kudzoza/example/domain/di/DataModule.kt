package id.kudzoza.example.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.kudzoza.example.domain.preference.GlobalPreference
import id.kudzoza.example.domain.repository.MovieRepository
import id.kudzoza.example.domain.usecase.MovieUseCase

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideMovieUseCase(
        movieRepository: MovieRepository,
        globalPreference: GlobalPreference,
    ): MovieUseCase {
        return MovieUseCase(movieRepository, globalPreference)
    }

}