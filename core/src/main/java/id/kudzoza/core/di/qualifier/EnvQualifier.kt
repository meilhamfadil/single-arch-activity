package id.kudzoza.core.di.qualifier

import javax.inject.Qualifier

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnvApiUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnvApplicationAuthorities

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnvApplicationId

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnvVersionCode

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnvVersionName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EnvDatabaseName