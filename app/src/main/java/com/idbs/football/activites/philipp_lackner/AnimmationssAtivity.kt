package com.idbs.football.activites.philipp_lackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.idbs.football.activites.philipp_lackner.ui.theme.FootballTheme

class AnimmationssAtivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SimpleAnimation()
                }
            }
        }
    }
}

@Composable
fun SimpleAnimation() {
    var sizeState by remember { mutableStateOf(200.dp) }
    val size by animateDpAsState(
        targetValue = sizeState,
        //animate 1
       /* tween(durationMillis = 3000,
        delayMillis = 300,
        easing = LinearEasing)*/
        //animate 2
/*    keyframes {
        durationMillis = 5000
        sizeState at 0 with LinearEasing
        sizeState * 1.5f at 1000 with FastOutLinearInEasing
        sizeState * 2f at 5000
    }*/
    //animate 3
    tween(
        durationMillis = 1000
    ))
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue =Color.Green ,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 2000,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(modifier = Modifier
        .size(size)
        .background(color),
    contentAlignment = Alignment.Center
){
    Button(onClick = { sizeState += 50.dp
    }) {
        Text(text = "Increase size")
    }
}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    FootballTheme {
    }
}