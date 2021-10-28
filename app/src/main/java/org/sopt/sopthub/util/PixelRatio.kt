package org.sopt.sopthub.util

import android.content.res.Resources
import android.util.TypedValue

class PixelRatio() {
    private val displayMetrics
        get() = Resources.getSystem().displayMetrics

    fun dpToPx(dp: Int) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics).toInt()

}

val Number.dp: Int
    get() = PixelRatio().dpToPx(this.toInt())




