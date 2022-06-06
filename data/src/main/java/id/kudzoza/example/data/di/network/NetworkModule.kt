package id.kudzoza.example.data.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kudzoza.core.di.qualifier.DefaultRetrofitClient
import id.kudzoza.example.data.repository.source.network.MovieService
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMovieService(
        @DefaultRetrofitClient retrofit: Retrofit,
    ): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}