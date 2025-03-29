package com.example.motion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.motion.theme.ComposeTheme
import com.naulian.modify.rememberIntState
import com.naulian.motion.AnimatedNumber
import com.naulian.motion.GradientText
import com.naulian.motion.MarqueeText
import com.naulian.motion.RippleCircle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {}
            }
        }
    }
}

@Composable
fun RippleCircleExample() {
    RippleCircle(rippleColor = Color.White, onClick = {})
}

@Composable
fun MarqueeTextExample() {
    MarqueeText(
        modifier = Modifier.padding(24.dp),
        text = "DO NOT PUSH PRODUCTION ON FRIDAY!",
        style = TextStyle(
            fontSize = 64.sp, lineHeight = 72.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
    )
}

@Composable
fun GradientTextExample() {
    GradientText(
        text = "works on my machine",
        style = TextStyle(
            fontSize = 96.sp, lineHeight = 110.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        colors = listOf(
            Color(0xFFF06292),
            Color(0xFF9575CD),
            Color(0xFF7986CB),
            Color(0xFF64B5F6),
        )
    )
}

@Composable
fun AnimatedNumberExample() {
    var number by rememberIntState(0)

    LaunchedEffect(Unit) {
        repeat(100) {
            number += Random.nextInt(-3, 31)
            delay(2500)
        }
    }

    AnimatedNumber(
        number = number.toLong(),
        textStyle = TextStyle(
            fontSize = 96.sp, lineHeight = 120.sp
        )
    )
}



