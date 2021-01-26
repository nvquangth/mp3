package com.bt.base.uikit

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.bt.base.R
import com.bt.base.extension.toPx
import kotlin.math.min


class CircularProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        val hexColors = arrayListOf("#FF4081", "#27A1D8", "#4DA945", "#F37D25")
        const val MAX_PART = 10
        const val MIN_PART = 2
        const val DURATION_DEFAULT = 1000
        const val STROKE_DEFAULT = 3F
        const val MIN_WIDTH = 36F
        const val HEX_COLOR_PATTERN = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$"
    }

    private var animationRotate: Animation? = null
    private var duration = DURATION_DEFAULT
    private var isReverse = false
    private var colors = arrayListOf<Int>()
    private var stroke = STROKE_DEFAULT
    private var isShowing = true

    init {

        context.theme.obtainStyledAttributes(attrs, R.styleable.CircularProgressView, 0, 0).apply {
            try {
                if (hasValue(R.styleable.CircularProgressView_cpColors)) {
                    getString(R.styleable.CircularProgressView_cpColors)
                        ?.split(",")
                        ?.map {
                            it.trim()
                        }
                        ?.filter {
                            it.isNotEmpty() && it.matches(Regex(HEX_COLOR_PATTERN))
                        }
                        ?.map {
                            Color.parseColor(it)
                        }
                        ?.let {
                            when {
                                it.size > MAX_PART -> {
                                    colors.addAll(it.take(MAX_PART))
                                }
                                it.size < MIN_PART -> {
                                    colors.addAll(hexColors.map { Color.parseColor(it) })
                                }
                                else -> {
                                    colors.addAll(it)
                                }
                            }
                        }
                } else {
                    colors.addAll(hexColors.map { Color.parseColor(it) })
                }

                duration = getInt(R.styleable.CircularProgressView_cpDuration, DURATION_DEFAULT)
                isReverse = getBoolean(R.styleable.CircularProgressView_cpReverse, false)
                stroke = getDimension(R.styleable.CircularProgressView_cpStroke, STROKE_DEFAULT.toPx(context).toFloat())
            } finally {
                recycle()
            }
        }

        animationRotate = AnimationUtils.loadAnimation(context, if (isReverse) R.anim.rotate_reverse else R.anim.rotate).apply {
            duration = this@CircularProgressView.duration.toLong()
            interpolator = LinearInterpolator()
            startAnimation(this)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT || layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(MIN_WIDTH.toPx(context), MIN_WIDTH.toPx(context))
        } else if (width != height) {
            setMeasuredDimension(min(width, height), min(width, height))
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val outTop = 0F
        val outLeft = 0F
        val outBottom = width.toFloat()
        val outRight = width.toFloat()
        val inTop = stroke
        val inLeft = stroke
        val inBottom = outBottom - inTop
        val inRight = outRight - inLeft

        val part = colors.size
        val k = 360F / part

        for (p in 0 until part) {
            val path = Path()
            path.arcTo(RectF(outLeft, outTop, outRight, outBottom), k * p, k)
            path.arcTo(RectF(inLeft, inTop, inRight, inBottom), (p + 1) * k % 360, -k)
            canvas?.drawPath(path, Paint().apply { color = colors[p] })
            path.close()
        }
    }

    fun dismiss() {
        if (isShowing) {
            isShowing = !isShowing
            clearAnimation()
            visibility = GONE
        }
    }

    fun show() {
        if (!isShowing) {
            isShowing = !isShowing
            startAnimation(animationRotate)
            visibility = INVISIBLE
        }
    }

    fun isShowing() = isShowing
}
