package id.kudzoza.example.domain.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kudzoza.core.di.qualifier.DefaultOkHttpClient
import id.kudzoza.core.di.qualifier.EnvApiUrl
import id.kudzoza.core.di.qualifier.DefaultRetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @DefaultRetrofitClient
    @Provides
    @Singleton
    fun provideRetrofitClient(
        @DefaultOkHttpClient okHttpClient: OkHttpClient,
        @EnvApiUrl baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}