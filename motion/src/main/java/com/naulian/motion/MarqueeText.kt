package com.naulian.motion

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MarqueeText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = TextStyle.Default
) {
    Text(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {
                drawContent()
                drawFadedEdge(leftEdge = true)
                drawFadedEdge(leftEdge = false)
            }
            .basicMarquee(iterations = Int.MAX_VALUE, velocity = 60.dp),
        text = text,
        style = style,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}

fun ContentDrawScope.drawFadedEdge(leftEdge: Boolean) {
    val edgeWidthPx = size.width * 0.06f
    drawRect(
        topLeft = Offset(if (leftEdge) 0f else size.width - edgeWidthPx, 0f),
        size = Size(edgeWidthPx, size.height),
        brush = Brush.horizontalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startX = if (leftEdge) 0f else size.width,
            endX = if (leftEdge) edgeWidthPx else size.width - edgeWidthPx
        ),
        blendMode = BlendMode.DstIn
    )
}

@Preview
@Composable
private fun HomeTopPartPreview() {
   MarqueeText(text = "Cin Khan Gin Naulian Kalein Hgee Bwar", style = MaterialTheme.typography.headlineLarge)
}