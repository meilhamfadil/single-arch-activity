package id.kudzoza.core.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import id.kudzoza.core.R
import id.kudzoza.core.ScreenObject

/**
 * Created by Kudzoza
 * on 03/08/2021
 **/

/**
 * Call this method (in onActivityCreated or later) to set
 * the width of the dialog to a percentage of the current
 * screen width.
 */
fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

/**
 * Call this method (in onActivityCreated or later)
 * to make the dialog near-full screen.
 */
fun DialogFragment.setFullScreen() {
    dialog?.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

fun Activity.showProgress() {
    ScreenObject.progressDialog = Dialog(this)
    ScreenObject.progressDialog?.setContentView(R.layout.app_progress)
    ScreenObject.progressDialog?.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    ScreenObject.progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    ScreenObject.progressDialog?.setCancelable(false)
    ScreenObject.progressDialog?.setCanceledOnTouchOutside(false)
    ScreenObject.progressDialog?.show()
}

fun Activity.showMessage(
    message: String,
    title: String = getString(R.string.app_message),
    action: (View) -> Unit = { ScreenObject.messageDialog?.dismiss() },
) {
    ScreenObject.messageDialog = Dialog(this)
    ScreenObject.messageDialog?.setContentView(R.layout.kdz_dialog_message)
    ScreenObject.messageDialog?.findViewById<TextView>(R.id.message)?.text = message
    ScreenObject.messageDialog?.findViewById<TextView>(R.id.title)?.text = title
    ScreenObject.messageDialog?.findViewById<TextView>(R.id.action_ok)?.setOnClickListener(action)
    ScreenObject.messageDialog?.window?.setLayout(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    ScreenObject.messageDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    ScreenObject.messageDialog?.setCanceledOnTouchOutside(false)
    ScreenObject.messageDialog?.show()
}

fun Activity.hideMessage() {
    ScreenObject.messageDialog?.dismiss()
}

fun Activity.hideProgress() {
    ScreenObject.progressDialog?.dismiss()
}

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .show()
}

fun View.showSnackBar(message: Int) {
    Snackbar.make(this, this.context.getString(message), Snackbar.LENGTH_LONG)
        .show()
}

fun Activity.hideKeyboardFrom(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0);
}

fun <T> catchToNull(action: () -> T?): T? {
    return try {
        action.invoke()
    } catch (e: Exception) {
        null
    }
}