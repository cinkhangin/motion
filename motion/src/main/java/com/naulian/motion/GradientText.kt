package com.naulian.motion

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalTextApi::class)
@Composable
fun GradientText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    colors: List<Color> = listOf(
        Color(0xFFD6EDFF),
        Color(0xFF1068DA),
        Color(0xFF2D80EB),
        Color(0xFFD6EDFF)
    )
) {

    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            return LinearGradientShader(
                colors = colors,
                from = Offset(offset * size.width, offset * size.height),
                to = Offset(offset * size.width + 200f, offset * size.height + 200f),
                tileMode = TileMode.Mirror
            )
        }
    }

    Text(
        text = text,
        style = style.copy(
            brush = brush,
        ),
        modifier = modifier
    )
}

@Preview
@Composable
fun GradientTextDemo() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .padding(16.dp),
           contentAlignment = Alignment.Center
        ) {
            GradientText(
                text = "Rainbow Text",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}