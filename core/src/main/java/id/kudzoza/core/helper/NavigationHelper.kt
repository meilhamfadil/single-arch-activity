package id.kudzoza.core.helper

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest

/**
 * Created by Kudzoza
 * on 04/06/2022
 **/

object NavigationHelper {

    fun NavController.navigateModule(uri: Uri) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(uri)
            .build()
        navigate(request)
    }

}