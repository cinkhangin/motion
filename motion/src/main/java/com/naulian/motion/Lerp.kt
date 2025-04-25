package com.naulian.motion

@Suppress("SpellCheckingInspection", "unused")
fun lerp(
    input: Float,
    inStart: Float = 0f,
    inEnd: Float = 1f,
    outStart: Float = 0f,
    outEnd: Float = 1f
): Float {
    if (input > inEnd) return outEnd
    if (input < inStart) return outStart
    return outStart + (outEnd - outStart) * (input - inStart) / (inEnd - inStart)
}