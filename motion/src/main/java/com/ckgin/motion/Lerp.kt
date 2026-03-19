package com.ckgin.motion

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

fun lerp(
    input: Int,
    inStart: Float = 0f,
    inEnd: Float = 1f,
    outStart: Float = 0f,
    outEnd: Float = 1f
): Float {
    return lerp(
        input.toFloat(),
        inStart,
        inEnd,
        outStart,
        outEnd
    )
}


