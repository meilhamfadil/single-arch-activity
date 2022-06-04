package id.kudzoza.example

import android.app.Application
import androidx.core.net.toUri
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
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

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this@MainApplication)

        val applicationId = BuildConfig.APPLICATION_ID
        AppNavigator.apply {
            featSplash = "android-app://$applicationId/splash".toUri()
            featMovies = "android-app://$applicationId/movie".toUri()
        }
    }

}