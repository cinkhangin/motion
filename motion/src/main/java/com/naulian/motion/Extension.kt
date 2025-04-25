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
    duration: Int = 1000,
    easing: Easing = LinearEasing,
    label: String,
    delay: Int = 0,
    startDelay: Int = 0,
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