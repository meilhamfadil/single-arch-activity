package id.kudzoza.core.compound

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import id.kudzoza.core.R
import id.kudzoza.core.util.areVisible

/**
 * Created by Kudzoza
 * on 09/02/2022
 **/

class PrimaryButton @JvmOverloads
constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseCompoundButton(ctx, attrs, defStyleAttr) {

    init {
        val layoutInflater = LayoutInflater.from(ctx)
        layoutInflater.inflate(R.layout.kdz_primary_button, this, true)

        ctx.theme.obtainStyledAttributes(attrs, R.styleable.BaseCompoundButton, 0, 0).apply {
            label.text = getText(R.styleable.BaseCompoundButton_text)

            getBoolean(R.styleable.BaseCompoundButton_loading, false).let {
                renderLoading(it)
            }

            getBoolean(R.styleable.BaseCompoundButton_enabled, isEnabled).let {
                container.isEnabled = it
                label.isEnabled = it
            }

            getDrawable(R.styleable.BaseCompoundButton_icon).let {
                if (it != null) {
                    icon.setImageDrawable(it)
                    icon.visibility = View.VISIBLE
                } else {
                    icon.visibility = View.GONE
                }
            }
        }
    }

    private fun renderLoading(value: Boolean) {
        progress.visibility =
            if (value) View.VISIBLE
            else View.GONE
        label.visibility =
            if (value) View.GONE
            else View.VISIBLE
        container.isEnabled = !value
        label.isEnabled = !value
    }

    fun setLoading(value: Boolean) {
        renderLoading(value)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        container.setOnClickListener(l)
    }

}




















