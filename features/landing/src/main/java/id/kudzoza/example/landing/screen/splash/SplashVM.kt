package id.kudzoza.example.landing.screen.splash

import androidx.lifecycle.ViewModel
import id.kudzoza.core.base.SingleLiveEvent
import id.kudzoza.core.util.launch
import id.kudzoza.core.util.main
import kotlinx.coroutines.delay

/**
 * Created by Kudzoza
 * on 04/09/2021
 **/

class SplashVM : ViewModel() {

    val moveToMain = SingleLiveEvent<Void>()

    init {
        launch {
            delay(2000)
            main {
                moveToMain.call()
            }
        }
    }

}