package id.kudzoza.example.data.di.localstorage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.kudzoza.core.di.qualifier.EnvApplicationId
import javax.inject.Singleton

/**
 * Created by Kudzoza
 * on 08/02/2022
 **/

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreference(
        @ApplicationContext context: Context,
        @EnvApplicationId applicationId: String,
    ): SharedPreferences {
        return context.getSharedPreferences(applicationId, MODE_PRIVATE)
    }

}