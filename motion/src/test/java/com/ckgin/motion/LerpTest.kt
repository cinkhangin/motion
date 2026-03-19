package com.ckgin.motion

import org.junit.Assert.assertEquals
import org.junit.Test

class LerpTest {
    @Test
    fun testLerp() {
        val actual = lerp(
            input = 10f,
            inStart = 0f,
            inEnd = 100f,
            outStart = 0f,
            outEnd = 10f
        )

        val expected = 1f
        assertEquals(expected, actual, 0.001f)
    }

    @Test
    fun testLerp2() {
        val actual = lerp(
            input = 10,
            inStart = 0f,
            inEnd = 100f,
            outStart = 0f,
            outEnd = 10f
        )

        val expected = 1f
        assertEquals(expected, actual, 0.001f)
    }
}
