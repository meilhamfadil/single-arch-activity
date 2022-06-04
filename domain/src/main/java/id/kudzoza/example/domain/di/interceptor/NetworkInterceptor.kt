package id.kudzoza.example.domain.di.interceptor

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.kudzoza.core.AppConfig
import id.kudzoza.core.di.qualifier.ChuckInterceptor
import id.kudzoza.core.di.qualifier.HeaderInterceptor
import id.kudzoza.core.di.qualifier.LoggerInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Created by Kudzoza
 * on 12/03/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object NetworkInterceptor {

    @LoggerInterceptor
    @Provides
    @Singleton
    fun provideLoggerInterceptorOkHttpClient(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @ChuckInterceptor
    @Provides
    @Singleton
    fun provideChuckInterceptorOkHttpClient(
        @ApplicationContext context: Context,
    ): Interceptor {
        val chuckInterceptor = ChuckerInterceptor.Builder(context)
        chuckInterceptor.collector(
            ChuckerCollector(
                context = context,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )
        )
        chuckInterceptor.maxContentLength(AppConfig.NETWORK_MAX_CONTENT_LENGTH)
        chuckInterceptor.redactHeaders(emptySet())
        chuckInterceptor.alwaysReadResponseBody(true)

        return chuckInterceptor.build()
    }

    @HeaderInterceptor
    @Provides
    @Singleton
    fun provideHeaderInterceptorOkHttpClient(): Interceptor {
        return Interceptor.invoke {
            val request = it.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()
            it.proceed(request)
        }
    }

}