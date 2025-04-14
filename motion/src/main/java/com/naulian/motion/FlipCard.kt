package com.naulian.motion

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naulian.modify.noRippleClick
import com.naulian.modify.rememberFloatState

sealed interface FlipOption {
    data object Vertical : FlipOption
    data object Horizontal : FlipOption
}


@Composable
fun FlipCard(
    modifier: Modifier = Modifier,
    onClickFront: () -> Unit = {},
    onClickBack: () -> Unit = {},
    frontContent: @Composable (BoxScope.() -> Unit),
    backContent: @Composable (BoxScope.() -> Unit),
    enableFlipOnClickFront: Boolean = true,
    enableFlipOnClickBack: Boolean = true,
    flipOption: FlipOption = FlipOption.Horizontal,
    shape: Shape = RoundedCornerShape(20.dp),
    color: Color = Color.White,
    contentAlignment: Alignment = Alignment.TopStart,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    flipAnimationSpec: AnimationSpec<Float> = tween(
        durationMillis = 1500,
        easing = LinearOutSlowInEasing
    ),
    flipCameraDistance: Float = 40f
) {

    var rotationTarget by rememberFloatState(0f)
    val rotation by animateFloatAsState(
        targetValue = rotationTarget,
        label = "cardFlip",
        animationSpec = flipAnimationSpec
    )

    //Front
    if (rotation <= 90) {
        Box(
            modifier = modifier
                .graphicsLayer {
                    if (flipOption == FlipOption.Vertical) {
                        rotationX = rotation
                    }
                    if (flipOption == FlipOption.Horizontal) {
                        rotationY = rotation
                    }
                    cameraDistance = flipCameraDistance
                }
                .background(color, shape)
                .clip(shape)
                .noRippleClick {
                    onClickFront()
                    if (enableFlipOnClickFront) {
                        rotationTarget = 180f
                    }
                }
                .padding(contentPadding),
            content = frontContent,
            contentAlignment = contentAlignment
        )
    }

    //Back
    if (rotation >= 90) {
        Box(
            modifier = modifier
                .graphicsLayer {
                    if (flipOption == FlipOption.Vertical) {
                        rotationX = 180 + rotation
                    }
                    if (flipOption == FlipOption.Horizontal) {
                        rotationY = 180 + rotation
                    }
                    cameraDistance = flipCameraDistance
                }
                .background(color, shape)
                .clip(shape)
                .noRippleClick {
                    onClickBack()
                    if (enableFlipOnClickBack) {
                        rotationTarget = 0f
                    }
                }
                .padding(contentPadding),
            content = backContent,
            contentAlignment = contentAlignment
        )
    }
}

@Preview
@Composable
private fun FlipCardPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF2F3F4))
        ) {
            FlipCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .padding(24.dp),
                flipOption = FlipOption.Vertical,
                frontContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFBBCCDC)), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Front", fontSize = 30.sp, color = Color.Black)
                    }
                },
                backContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFDCB6E0)), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Back", fontSize = 30.sp, color = Color.Black)
                    }
                },
                contentAlignment = Alignment.Center,
            )
        }
    }
}