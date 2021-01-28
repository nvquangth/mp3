package com.bt.base.math

import org.junit.Assert
import org.junit.Test
import kotlin.math.abs
import kotlin.math.sqrt

class MathTest {

    @Test
    fun test1() {
        val xM = 1F
        val yM = 1F
        val xA = 0F
        val yA = 0F
        val xB = 5F
        val yB = 0F

        Assert.assertEquals(1F, distanceFromMToAB(xM, yM, xA, yA, xB, yB))
    }

    @Test
    fun test2() {
        val xM = 0F
        val yM = 0F
        val xA = 0F
        val yA = 4F
        val xB = 3F
        val yB = 0F

        Assert.assertEquals(2.4F, distanceFromMToAB(xM, yM, xA, yA, xB, yB))
    }

    @Test
    fun test3() {
        val xM = 4F
        val yM = 2F
        val xA = 3F
        val yA = 3F
        val xB = 5F
        val yB = 7F

        Assert.assertEquals(1.3416407F, distanceFromMToAB(xM, yM, xA, yA, xB, yB))
    }

    private fun distanceFromMToAB(xM: Float, yM: Float, xA: Float, yA: Float, xB: Float, yB: Float): Float {
        val a = yA - yB
        val b = xB - xA
        val c = (yB - yA) * xA + (xA - xB) * yA
        val r = abs(a * xM + b * yM + c) / sqrt(a * a + b * b)
        return r
    }
}
