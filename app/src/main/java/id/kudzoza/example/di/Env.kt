package id.kudzoza.example.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kudzoza.core.di.qualifier.*
import id.kudzoza.example.BuildConfig
import javax.inject.Singleton

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object Env {

    @EnvApiUrl
    @Provides
    @Singleton
    fun provideApiUrl(): String = BuildConfig.API_URL

    @EnvApplicationId
    @Provides
    @Singleton
    fun provideApplicationId(): String = BuildConfig.APPLICATION_ID

    @EnvVersionName
    @Provides
    @Singleton
    fun provideVersionName(): String = BuildConfig.VERSION_NAME

    @EnvVersionCode
    @Provides
    @Singleton
    fun provideVersionCode(): Int = BuildConfig.VERSION_CODE

    @EnvApplicationAuthorities
    @Provides
    @Singleton
    fun provideApplicationAuthorities(): String = BuildConfig.APPLICATION_ID + ".provider"

    @EnvDatabaseName
    @Provides
    @Singleton
    fun provideDatabaseName(): String = BuildConfig.DATABASE_NAME

}