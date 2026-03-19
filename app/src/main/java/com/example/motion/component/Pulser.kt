package com.example.motion.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp

@Composable
fun Pulser(modifier: Modifier = Modifier, color: Color = Color.Red) {
    val transition = rememberInfiniteTransition(label = "ripple")

    val animator by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = "radius"
    )

    Canvas(
        modifier = modifier.size(300.dp)
    ) {
        val center = Offset(size.width / 2, size.height / 2)

        // Draw the solid circle
        drawCircle(
            color = color,
            radius = 150f,
            center = center
        )


        for(i in 0 until 6){
            // Calculate a staggered phase for this specific ripple
            val phase = (animator + (i.toFloat() / 6)) % 1f

            // Use phase to calculate radius and transparency
            val radius = lerp(0f, size.minDimension / 2, phase)
            val alpha = lerp(0.5f, 0f, phase) // Fade out as it expands

            drawCircle(
                color = color.copy(alpha),
                radius = radius,
                alpha = alpha
            )
        }
    }
}

@Preview
@Composable
private fun PulserPreview() {
    Pulser()
}