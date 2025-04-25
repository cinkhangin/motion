package com.naulian.motion

import androidx.compose.animation.core.CubicBezierEasing

//compose built-in Easing
//val FastOutSlowInEasing: Easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)
//val LinearOutSlowInEasing: Easing = CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f)
//val FastOutLinearInEasing: Easing = CubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f)
//val LinearEasing: Easing = Easing { fraction -> fraction }

@Suppress("unused")
val SlowOutLinearInEasing = CubicBezierEasing(0.8f, 0.0f, 1.0f, 1.0f)
@Suppress("unused")
val SlowOutFastInEasing = CubicBezierEasing(0.8f, 0.0f, 0.6f, 1.0f)