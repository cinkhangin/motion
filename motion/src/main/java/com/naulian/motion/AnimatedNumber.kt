package com.naulian.motion

import androidx.annotation.IntRange
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import com.naulian.modify.rememberIntState
import com.naulian.modify.rememberLongState
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun AnimatedNumber(
    modifier: Modifier = Modifier,
    number: Long,
    digitCount: Int = 5,
    fontSize: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    val numberString = abs(number).toString().takeLast(digitCount)
    val numbers = buildString {
        if (numberString.length < digitCount) {
            repeat(digitCount - numberString.length) {
                append("0")
            }
        }
        append(numberString)
    }.map { it.digitToInt() }


    val style by remember {
        val fs = if (fontSize.isSpecified) fontSize
        else if (textStyle.fontSize.isSpecified) textStyle.fontSize
        else 16.sp

        val lh = if (lineHeight.isSpecified) lineHeight
        else if (textStyle.lineHeight.isSpecified) textStyle.lineHeight
        else 20.sp

        mutableStateOf(
            textStyle.copy(
                fontSize = fs,
                lineHeight = lh
            )
        )
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(0.dp)) {
        numbers.fastForEach { value ->
            SingleAnimatedNumber(value, number, style)
        }
    }
}

@Preview
@Composable
private fun NumberPreview() {

    var number by rememberIntState(0)

    LaunchedEffect(Unit) {
        repeat(1000) {
            number += 9
            delay(3000)
        }
    }

    AnimatedNumber(number = number.toLong(), textStyle = TextStyle.Default)
}

@Composable
fun SingleAnimatedNumber(
    @IntRange(0, 9) value: Int,
    fullNumber: Long,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {

    val scrollState = rememberLazyListState()

    val lineHeight: TextUnit = textStyle.lineHeight.value.sp

    val heightPx: Float
    val heightDp: Dp
    LocalDensity.current.apply {
        heightPx = lineHeight.toPx()
        heightDp = (heightPx / density).dp
    }

    var prevFullNumber by rememberLongState(-1L)
    var prevValue by rememberIntState(0)

    val columnHeight = heightPx * 10

    LaunchedEffect(value) {
        if (prevFullNumber == -1L) {
            scrollState.scrollToItem(value)
            prevFullNumber = fullNumber
            prevValue = value
            return@LaunchedEffect
        }

        var distance = abs(prevValue - value).toFloat()
        if (distance == 0f) {
            return@LaunchedEffect
        }

        var scrollDown = value < prevValue
        if (fullNumber > prevFullNumber && value < prevValue) { //scroll up
            scrollState.scrollToItem(0)
            scrollState.scrollToItem(prevValue)
            distance = (10f - prevValue) + value //
            scrollDown = false
        }

        if (fullNumber < prevFullNumber && value > prevValue) { // scroll down
            scrollState.scrollToItem(0)
            scrollState.scrollToItem(10 + prevValue)
            distance = ((10f + prevValue) - value)
            scrollDown = true

        }

        prevValue = value
        prevFullNumber = fullNumber

        val target = columnHeight * (distance / 10f)
        scrollState.animateScrollBy(
            value = if (scrollDown) -target else target,
            animationSpec = tween(
                durationMillis = 1500,
                easing = CubicBezierEasing(0.1f, 0.0f, 0.2f, 1.0f)
            )
        )
    }

    Box(modifier = Modifier.height(heightDp)) {
        val textHeight: Dp
        LocalDensity.current.apply {
            textHeight = lineHeight.toDp()
        }

        LazyColumn(state = scrollState) {
            items(20, key = { it }) {
                Text(
                    modifier = Modifier.height(textHeight),
                    text = (it % 10).toString(),
                    style = TextStyle.Default.copy(
                        fontSize = textStyle.fontSize,
                        lineHeight = textStyle.lineHeight,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun AutoNumberPreview() {
    SingleAnimatedNumber(5, 0, textStyle = TextStyle.Default)
}
