package com.idbs.football.activites.philipp_lackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import com.idbs.football.activites.philipp_lackner.ui.theme.FootballTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idbs.football.R
import kotlinx.coroutines.launch
import kotlin.random.Random

class philipp_tutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
         //   FootballTheme {
                val painter = painterResource(id = R.drawable.cat)
                val description = "Kermit playing in the show"
                val title = "kermit is palying in the show"
                val color = remember {
                    mutableStateOf(Color.Yellow)
                }
                //Image Card design
          /*      Box(modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(16.dp)){
                    ImageCard(painter = painter, contentDescription = description, title = title)

                }*/
                //Text Styling
    /*            Box(modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()){
                    TextStyling()
                }*/
                //State
   /*             Column(modifier = Modifier.fillMaxSize()) {
                    //   ColorBox(modifier = Modifier.fillMaxSize())
                    ColorBox2(modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()){
                        color.value = it
                    }
                    Box(modifier = Modifier
                        .background(color = color.value)
                        .weight(1f)
                        .fillMaxSize())
                }*/
                //Working with scaffoldState and design EditText with Snackbar
                TextFieldAndSnackBar()
         //   }
        }
    }
}

@Composable
fun TextFieldAndSnackBar(){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var textFieldState by remember {
        mutableStateOf("")
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
    scaffoldState = scaffoldState) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
        ) {
        TextField(value = textFieldState,
            label = { Text(text = "Enter your name") },
            onValueChange ={ textFieldState = it },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { 
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldState")
                }
            }) {
                Text(text = "Pls grat me")
            }

        }
    }
}
@Composable
fun ColorBox2(modifier: Modifier,
updateColor: (Color) -> Unit ){
    //This fun for State concept

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(), 1f
                )
            )
        }
    )
}

@Composable
fun ColorBox(modifier: Modifier){
    //This fun for State concept
    val color = remember {
        mutableStateOf(Color.Yellow)
    }
    Box(modifier = modifier
        .background(color.value)
        .clickable {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(), 1f
            )
        }
    )
}

@Composable
fun TextStyling(){
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Green,
            fontSize = 50.sp
        )
        ){
            append("J")
        }
        append("etpack ")
    },
        color = Color.White,
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
        //fontFamily = fontFamily
    )
}

@Composable
fun ImageCard(painter:Painter,
              contentDescription:String,
              title:String,
              modifier:Modifier = Modifier
){
    Card(modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            //Gradient transparent and black
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 300f
                    )
                ))
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(text =  title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    FootballTheme {
    }
}