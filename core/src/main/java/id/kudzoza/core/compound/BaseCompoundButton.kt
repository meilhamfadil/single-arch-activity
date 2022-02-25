package id.kudzoza.core.compound

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import id.kudzoza.core.R

/**
 * Created by Kudzoza
 * on 10/02/2022
 **/

abstract class BaseCompoundButton @JvmOverloads
constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    val icon: ImageView get() = this.findViewById(R.id.icon)
    val label: TextView get() = this.findViewById(R.id.label)
    val progress: ProgressBar get() = this.findViewById(R.id.progress)
    val container: LinearLayout get() = this.findViewById(R.id.container)

}