package com.bt.base.extension

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

/**
 * ========== FLOAT ==========
 */
fun Float.toPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    ).roundToInt()
}

/**
 * ========== INT ==========
 */
fun Int.toDuration(): String {
    val s: Int = this % 60
    val m: Int = this / 60 % 60
    val h: Int = this / (60 * 60) % 24

    return when {
        h > 0 -> String.format("%d:%02d:%02d", h, m, s)
        m > 0 -> String.format("%02d:%02d", m, s)
        else -> String.format("%02d", s)
    }
}
