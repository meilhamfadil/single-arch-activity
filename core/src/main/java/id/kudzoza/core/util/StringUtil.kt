package id.kudzoza.core.util

import android.text.TextUtils
import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Kudzoza
 * on 19/09/2021
 **/

fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}