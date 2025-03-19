package com.example.motion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motion.theme.ComposeTheme
import com.naulian.modify.rememberIntState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {
                    composable<Home> {
                        HomeScreen(navController)
                    }
                    composable<Second> {
                        SecondScreen(navController)
                    }
                }
            }
        }
    }
}

@Serializable
object Home

@Serializable
object Second

@Composable
fun HomeScreen(navController: NavController) {





    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var number by rememberIntState(0)

        LaunchedEffect(Unit) {
            repeat(100) {
                number += Random.nextInt(-3, 31)
                delay(2500)
            }
        }

        Number(
            number = number.toLong(),
            textStyle = TextStyle(
                fontSize = 96.sp, lineHeight = 120.sp
            )
        )
    }







}

@Composable
fun SecondScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text("Back Home")
        }
    }
}