package com.idbs.football.activites.philipp_lackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import com.idbs.football.activites.philipp_lackner.ui.theme.FootballTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idbs.football.R

class philipp_tutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTheme {
                val painter = painterResource(id = R.drawable.cat)
                val description = "Kermit playing in the show"
                val title = "kermit is palying in the show"
                Box(modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(16.dp)){
                    ImageCard(painter = painter, contentDescription = description, title = title)

                }

                // A surface container using the 'background' color from the theme
            /*    Surface(color = MaterialTheme.colors.background) {
                    
                }*/
            }
        }
    }
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
            Box(modifier = Modifier.fillMaxSize()
                .background(
                    Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black),
                    startY = 300f)))
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