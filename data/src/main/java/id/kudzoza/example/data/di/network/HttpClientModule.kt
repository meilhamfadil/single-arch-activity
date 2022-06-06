package id.kudzoza.example.data.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kudzoza.core.AppConfig.NETWORK_CONNECTION_TIMEOUT
import id.kudzoza.core.AppConfig.NETWORK_READ_TIMEOUT
import id.kudzoza.core.BuildConfig
import id.kudzoza.core.di.qualifier.ChuckInterceptor
import id.kudzoza.core.di.qualifier.HeaderInterceptor
import id.kudzoza.core.di.qualifier.LoggerInterceptor
import id.kudzoza.core.di.qualifier.DefaultOkHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @DefaultOkHttpClient
    @Provides
    fun provideOkHttpClient(
        @LoggerInterceptor logger: Interceptor,
        @ChuckInterceptor chuck: Interceptor,
        @HeaderInterceptor header: Interceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(header)
            .readTimeout(NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_CONNECTION_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClient
                .addInterceptor(logger)
                .addInterceptor(chuck)
        }

        return okHttpClient.build()
    }

}