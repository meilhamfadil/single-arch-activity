package id.kudzoza.core.helper

import android.net.Uri
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import id.kudzoza.core.AppNavigator

/**
 * Created by Kudzoza
 * on 04/06/2022
 **/

object NavigationHelper {

    fun NavController.navigateModule(
        uri: Uri,
        args: Bundle = bundleOf(),
        navOptions: NavOptions? = null,
    ) {
        val uriBuilder = uri.buildUpon()
        for (key in args.keySet())
            uriBuilder.appendQueryParameter(key, args.get(key).toString())
        val request = NavDeepLinkRequest.Builder
            .fromUri(uriBuilder.build())
            .build()
        navigate(request, navOptions)
    }

    fun NavController.openGlobalNotFound(
        message: String = "",
        keyword: String = "",
        navOptions: NavOptions? = null,
    ) {
        navigateModule(
            AppNavigator.globalNotFound,
            bundleOf(
                "message" to message,
                "keyword" to keyword
            ),
            navOptions
        )
    }

}