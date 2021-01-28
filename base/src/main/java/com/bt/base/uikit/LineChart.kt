package com.bt.base.uikit

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.bt.base.extension.toPx
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class LineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        background = ColorDrawable(Color.TRANSPARENT)
    }

    private var datas: List<List<Pair<Long, Long>>> = emptyList()
    private var points: ArrayList<ArrayList<PointF>> = arrayListOf()

    // số line được vẽ
    private var nTop = 0

    // line được highlight
    private var nHighlight = 0

    // max số lượng thời gian
    private var maxX = 23

    // max điểm cho 1 line
    private var maxY = 0L
    private var radiusDot = 5F
    private var unitX = 0F
    private var unitY = 0F
    private val colors = listOf("#5d88cc", "#cb613a", "#259667")
    private var colorDot = "#FFFFFF"
    private var strokeDot = 3F

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        unitX = (width - radiusDot * 2 - strokeDot * 2) / maxX
        unitY = (height - radiusDot * 2 - strokeDot * 2) / maxY

        points.clear()

        for (index in 0 until nTop) {

            val pointLine = ArrayList<PointF>()

            val dataLine = datas[index]
            for (i in 0 until dataLine.size - 1) {

                // draw line
                val startX = i * unitX + radiusDot + strokeDot
                val startY = height - dataLine[i].second * unitY - radiusDot - strokeDot
                val stopX = (i + 1) * unitX + radiusDot + strokeDot
                val stopY = height - dataLine[i + 1].second * unitY - radiusDot - strokeDot

                canvas?.drawLine(startX, startY, stopX, stopY,
                    Paint().apply {
                        color = Color.parseColor(colors[index])
                        strokeWidth = 5F
                        isAntiAlias = true
                    }
                )

                if (nHighlight == index) {

                    // draw dot between 2 line
                    canvas?.drawCircle(startX, startY, radiusDot,
                        Paint().apply {
                            color = Color.parseColor(colorDot)
                            isAntiAlias = true
                        }
                    )
                    canvas?.drawPath(Path().apply {
                        arcTo(RectF(startX - radiusDot - strokeDot, startY - radiusDot - strokeDot, startX + radiusDot + strokeDot, startY + radiusDot + strokeDot), 0F, 359F)
                        arcTo(RectF(startX - radiusDot, startY - radiusDot, startX + radiusDot, startY + radiusDot), 0F, -359F)
                    }, Paint().apply {
                        color = Color.parseColor(colors[index])
                        isAntiAlias = true
                    })

                    if (i == dataLine.size - 2) {
                        canvas?.drawCircle(stopX, stopY, radiusDot,
                            Paint().apply {
                                color = Color.parseColor(colorDot)
                                isAntiAlias = true
                            }
                        )
                        canvas?.drawPath(Path().apply {
                            arcTo(RectF(stopX - radiusDot - strokeDot, stopY - radiusDot - strokeDot, stopX + radiusDot + strokeDot, stopY + radiusDot + strokeDot), 0F, 359F)
                            arcTo(RectF(stopX - radiusDot, stopY - radiusDot, stopX + radiusDot, stopY + radiusDot), 0F, -359F)
                        }, Paint().apply {
                            color = Color.parseColor(colors[index])
                            isAntiAlias = true
                        })
                    }
                }

                pointLine.add(PointF(startX, startY))
                if (i == dataLine.size - 2) {
                    pointLine.add(PointF(stopX, stopY))
                }
            }
            points.add(pointLine)
        }

        canvas?.drawLine(0F, height - 60F, width.toFloat(), height - 60F,
            Paint().apply {
                color = Color.WHITE
                strokeWidth = 3F
                alpha = 70
            })

        val textPaint = Paint().apply {
            textSize = 14F.toPx(context).toFloat()
            color = Color.WHITE
            alpha = 70
        }
        datas[0].forEachIndexed { index, pair ->
            canvas?.drawText(pair.first.toString(), index * unitX, height.toFloat(), textPaint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                val l = findLineNeedHighlight(PointF(event.x, event.y))
                if (l != -1) {
                    setHighlight(l)
                }
            }
        }
        return true
    }

    fun setData(d: List<List<Pair<Long, Long>>>) {
        datas = d
        initDefaultValue()
        invalidate()
    }

    /**
     * set duong thang duoc highlight
     * @param h
     */
    fun setHighlight(h: Int) {
        if (h in 0..nTop) {
            nHighlight = h
            invalidate()
        }
    }

    fun getHighlight() = nHighlight

    private fun initDefaultValue() {
        nTop = datas.size
        nHighlight = 0
        maxX = datas[0].size - 1

        datas.forEach {
            (it.maxByOrNull { it.second }?.second ?: 0).let {
                maxY = max(it, maxY)
            }
        }
    }

    /**
     * Khoang cach tu 1 diem M den duong thang AB
     * @param xM
     * @param yM
     * @param xA
     * @param yA
     * @param xB
     * @param yB
     */
    private fun distanceFromMToAB(xM: Float, yM: Float, xA: Float, yA: Float, xB: Float, yB: Float): Float {
        val a = yA - yB
        val b = xB - xA
        val c = (yB - yA) * xA + (xA - xB) * yA
        return abs(a * xM + b * yM + c) / sqrt(a * a + b * b)
    }

    /**
     * Tim duong thang se duoc highlight
     * @param pointClicked diem duoc click
     */
    private fun findLineNeedHighlight(pointClicked: PointF): Int {
        // sai so (+- k) khoang cach tu diem clicked den duong thang
        val k = 20F
        for (i in 0 until points.size) {
            val pointLine = points[i]
            for (index in 0 until (pointLine.size - 1)) {

                val d = distanceFromMToAB(pointClicked.x, pointClicked.y, pointLine[index].x, pointLine[index].y, pointLine[index + 1].x, pointLine[index + 1].y)

                val xMax = max(pointLine[index].x, pointLine[index + 1].x)
                val xMin = min(pointLine[index].x, pointLine[index + 1].x)
                val yMax = max(pointLine[index].y + k, pointLine[index + 1].y + k)
                val yMin = min(pointLine[index].y - k, pointLine[index + 1].y - k)

                if (pointClicked.x in xMin..xMax && pointClicked.y in yMin..yMax && d <= k) return i
            }
        }
        return -1
    }
}
