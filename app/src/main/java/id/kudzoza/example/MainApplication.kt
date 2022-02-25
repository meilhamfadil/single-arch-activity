package id.kudzoza.example

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import id.kudzoza.core.AppNavigator
import id.kudzoza.core.di.qualifier.EnvApplicationId
import java.util.*
import javax.inject.Inject


/**
 * Created by Kudzoza
 * on 04/09/2021
 **/

@HiltAndroidApp
class MainApplication : Application() {

    @EnvApplicationId
    @Inject
    lateinit var applicationId: String

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this@MainApplication)

        AppNavigator.apply {

            featSplash = "android-app://$applicationId/splash"

            featMovies = "android-app://$applicationId/movie"

        }
    }

}