package com.idbs.football.activites.philipp_lackner

import android.os.Bundle
import android.view.MotionEvent
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.idbs.football.R
import com.idbs.football.activites.philipp_lackner.ui.theme.FootballTheme
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
class AnimmationssAtivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()){
                        CircularProgressBar(percentage = 0.8f, number = 100 )
                    }
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF101010))
                    ) {
                     Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                     modifier = Modifier
                         .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                         .padding(30.dp)
                     ) {
                         var valume by remember {
                             mutableStateOf(0f)
                         }
                         val barCount = 20
                         MusicKnob(
                             modifier = Modifier.size(100.dp)){
                             valume = it
                         }
                         Spacer(modifier = Modifier.width(20.dp))
                         ValumeBar(modifier = Modifier.fillMaxWidth()
                             .height(30.dp),
                             activeBars = (barCount * valume).roundToInt(),
                             barCount = barCount
                         )
                     }
                    }
                }
            }
        }
    }
}



//Draggable music knob
@Composable
fun ValumeBar(
    modifier: Modifier = Modifier,
    activeBars:Int = 0,
    barCount:Int = 10
){
    BoxWithConstraints(contentAlignment = Alignment.Center,
    modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier ){
            for (i in 0 until barCount){
                drawRoundRect(color = if (i in 0..activeBars) Color.Green else Color.DarkGray ,
                topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                size = Size(barWidth,constraints.maxHeight.toFloat()),
                cornerRadius = CornerRadius(0f))
            }
        }
    }
}
@ExperimentalComposeUiApi
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitinAngle:Float = 25f,
    onValueChange: (Float) -> Unit){
    var rotation by remember{
        mutableStateOf(limitinAngle)
    }
    var touchX by remember{
        mutableStateOf(0f)
    }

    var touchY by remember{
        mutableStateOf(0f)
    }

    var centerX by remember{
        mutableStateOf(0f)
    }
    var centerY by remember{
        mutableStateOf(0f)
    }

    Image(painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music knob",
        modifier = modifier
            .fillMaxSize()
            //Once the position of this img, finds us its cordinate
            .onGloballyPositioned {
                val windoeBound =
                    it.boundsInWindow() // refer to bounris and position of the view relative to the whole screen
                centerX = windoeBound.size.width / 2f
                centerY = windoeBound.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()
                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitinAngle..limitinAngle) {
                            val fixedAngle = if (angle in -180f..-limitinAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle
                            val percent = (fixedAngle - limitinAngle) / (360 - 2 * limitinAngle)
                            onValueChange(percent)
                            true
                        } else false

                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )


}
//-----------------------------------
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