package id.kudzoza.core.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import id.kudzoza.core.AppConfig
import id.kudzoza.core.R

/**
 * Created by Kudzoza
 * on 04/09/2021
 **/

fun MotionLayout.onTransitionReachEnd(id: Int, action: () -> Unit) {
    this.addTransitionListener(object : MotionLayout.TransitionListener {
        override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            if (id == currentId)
                action.invoke()
        }

        override fun onTransitionStarted(
            motionLayout: MotionLayout?,
            startId: Int,
            endId: Int,
        ) {
        }

        override fun onTransitionChange(
            motionLayout: MotionLayout?,
            startId: Int,
            endId: Int,
            progress: Float,
        ) {
        }

        override fun onTransitionTrigger(
            motionLayout: MotionLayout?,
            triggerId: Int,
            positive: Boolean,
            progress: Float,
        ) {
        }
    })
}

var TextInputLayout.message: String
    get() = this.error.toString()
    set(text) {
        this.error = text
        this.isErrorEnabled = text.isNotEmpty()
    }

fun createOtpInput(vararg editText: EditText, action: (String) -> Unit) {
    editText.forEachIndexed { i: Int, input: EditText ->
        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (i == (editText.size - 1)) {
                    var otp = ""
                    editText.forEach { otp += it.text }
                    if (otp.length == editText.size) {
                        action.invoke(otp)
                    }
                } else if (input.text.isNotEmpty()) {
                    editText[i + 1].requestFocus()
                }
            }
        })

        input.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && input.text.isNotEmpty())
                input.setText("")
        }

        input.setOnKeyListener { _, keyCode, event ->
            if (event.action != KeyEvent.ACTION_DOWN)
                return@setOnKeyListener false
            if (keyCode == KeyEvent.KEYCODE_DEL && i != 0)
                editText[i - 1].requestFocus()
            return@setOnKeyListener false
        }
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Boolean.areVisible(visibility: Int = View.GONE): Int =
    if (this) View.VISIBLE
    else visibility

fun watchEditTexts(
    vararg pairs: Pair<TextInputEditText, TextInputLayout>,
    finishAction: () -> Unit,
) {
    pairs.forEach {
        it.first.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                it.first.removeTextChangedListener(this)
                it.second.message = ""
                finishAction.invoke()
                it.first.addTextChangedListener(this)
            }
        })
    }
}

fun watchEditText(
    vararg pairs: TextInputEditText,
    finishAction: () -> Unit,
) {
    pairs.forEach {
        it.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                it.removeTextChangedListener(this)
                finishAction.invoke()
                it.addTextChangedListener(this)
            }
        })
    }
}

fun RecyclerView.onTouchEnd(
    otherCondition: Boolean = true,
    action: () -> Unit,
) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = this@onTouchEnd.layoutManager
            val visibleCount = layoutManager?.childCount ?: 0
            val totalCount = layoutManager?.itemCount ?: 0
            val firstVisible =
                (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            if (otherCondition && (
                        (visibleCount + firstVisible) >= totalCount &&
                                firstVisible >= 0 &&
                                totalCount >= AppConfig.DEFAULT_API_LIMIT
                        )
            ) action.invoke()
        }
    })
}

fun AppCompatAutoCompleteTextView.setOnItemSelected(action: () -> Unit) {
    this.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
        action.invoke()
    }
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            action.invoke()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            action.invoke()
        }
    }
}

fun Context.adapterView(arrayResource: Int): ArrayAdapter<CharSequence> {
    return ArrayAdapter.createFromResource(
        this,
        arrayResource,
        R.layout.app_dropdown_item,
    )
}

fun Context.drawable(resource: Int) = ContextCompat.getDrawable(this, resource)

fun Context.color(resource: Int) = ContextCompat.getColor(this, resource)