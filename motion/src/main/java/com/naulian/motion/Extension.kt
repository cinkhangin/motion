package com.naulian.motion

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

@Suppress("unused")
@Composable
fun InfiniteTransition.animateSimpleFloat(
    label: String,
    initialValue: Float = 0f,
    targetValue: Float = 1f,
    duration: Int = 1000,
    delay: Int = 0,
    startDelay: Int = 0,
    easing: Easing = LinearEasing,
    repeatMode: RepeatMode = RepeatMode.Reverse
) = animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = InfiniteRepeatableSpec(
        animation = tween(
            durationMillis = duration,
            easing = easing,
            delayMillis = delay,
        ),
        repeatMode = repeatMode,
        initialStartOffset = StartOffset(startDelay)
    ),
    label = label,
)