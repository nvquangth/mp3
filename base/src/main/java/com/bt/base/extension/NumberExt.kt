package com.bt.base.extension

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

fun Float.toPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    ).roundToInt()
}
