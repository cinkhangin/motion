package com.naulian.motion

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RippleCircle(
    color: Color = Gray,
    modifier: Modifier = Modifier.size(300.dp),
    circleSize: Dp = 193.dp,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition()


    val ripple1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val ripple2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(750)
        ),
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val shadowPadding = 100
            val center = Offset(size.width / 2, size.height / 2)
            val maxRadius = ((size.minDimension - shadowPadding) / 2)
            val initialRadius = (circleSize.toPx() - shadowPadding) / 2

            listOf(ripple1, ripple2)
                .filter { it > 0f }
                .sortedDescending()
                .forEachIndexed { index, value ->
                    val radius = initialRadius + (maxRadius - initialRadius) * value
                    val alpha = ((1f - value) * 1f).coerceIn(0f, 1f)

                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Black.copy(alpha = alpha),
                                Color.Transparent
                            ),
                            center = center,
                            radius = radius + (shadowPadding / 2)
                        )
                    )

                    drawCircle(
                        color = color,
                        radius = radius,
                        center = center,
                    )
                }
        }

        Box(
            modifier = Modifier
                .size(circleSize)
                .background(color = Color.White, RoundedCornerShape(50))
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}

@Preview
@Composable
fun RippleDemo() {

    val color = Color(0xFF0091EA)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        RippleCircle(color = color, onClick = {})
    }
}