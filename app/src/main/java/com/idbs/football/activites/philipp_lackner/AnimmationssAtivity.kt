package com.idbs.football.activites.philipp_lackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.idbs.football.activites.philipp_lackner.ui.theme.FootballTheme

class AnimmationssAtivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                  //  SimpleAnimation()
                    Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()){
                        CircularProgressBar(percentage = 0.8f, number = 100 )
                    }
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

@Composable
fun CircularProgressBar(
    percentage:Float,
    number:Int,
    fontSize:TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color:Color = Color.Green,
    strokeWidth:Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
){
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true ){ animationPlayed = true }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f) ){
        Canvas(modifier = Modifier.size(radius * 2f)){
            drawArc(color = color,
            -90f,
            360 * curPercentage.value,
            useCenter = false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(text = (curPercentage.value * number).toInt().toString(),
        color = Color.Black, fontSize = fontSize,
        fontWeight = FontWeight.Bold
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    FootballTheme {
    }
}