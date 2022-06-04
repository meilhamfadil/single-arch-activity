package id.kudzoza.core.di.qualifier

import javax.inject.Qualifier

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggerInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChuckInterceptor