package com.idbs.football

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.idbs.football.ui.theme.FootballTheme

class ComposeActivity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
            //Column like linearLayout
            Column (modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                MyText(myText.value)
                MyTextField()
                MyTOutLineTextField()
                MyBtn()
                MyRadioBtn()
                MyFloatBtn()
                MyProgress()
            }
        }
    }
}
val myText = mutableStateOf("")

@Composable
fun MyText(text:String){
    Text(text = "$text by sohir",
        color = Color.Blue,
        fontSize = 24.sp)
}
@Composable
fun MyTextField(){
    //Remember concatenate the text chars
    val textValue = remember {
        mutableStateOf("")
    }
    TextField(
        value = textValue.value,
        onValueChange = {
        text -> textValue.value = text
    },
        label = {
            Text(text = "this text field")
        }
    )
}
@Composable
fun MyTOutLineTextField(){
    //Remember concatenate the text chars
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textValue.value,
        onValueChange = { textValue.value = it },
        label = { Text(text = "this text field") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = colorResource(id = R.color.teal_700),
            focusedBorderColor = colorResource(id = R.color.teal_200),
            unfocusedBorderColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
        )
    )
}

@Preview
@Composable
fun MyBtn(){
    var clickNum = 0
    Button(onClick = {
        myText.value = "My Click num ${clickNum++}"
    },
    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_200),),
        border = BorderStroke(2.dp, colorResource(id = R.color.teal_700))
//        contentPadding = PaddingValues(10.dp,2.dp)
//        modifier = Modifier.padding(10.dp,2.dp)
    ) {
        Text(text = stringResource(id = R.string.btn_placeholder), color = colorResource(id = R.color.white))


    }
}

@Composable
fun MyRadioBtn(){
    val radioBtn = listOf(0,1,2)
    val selectedBtn = remember {
        mutableStateOf(radioBtn[0])
    }
    radioBtn.forEach { index->
        val isSelected = index == selectedBtn.value
        val colors = RadioButtonDefaults.colors(
            selectedColor = colorResource(id = R.color.purple_200),
            unselectedColor = colorResource(id = R.color.black),
            disabledColor = colorResource(id = R.color.teal_700)
        )
        RadioButton(selected = isSelected, onClick = { selectedBtn.value = index }, colors = colors)

    }
}

@Composable
fun MyFloatBtn(){
    FloatingActionButton(onClick = { /*TODO*/ },
    contentColor = Color.Yellow,
    content = {
        Icon(Icons.Filled.Favorite, stringResource(id = R.string.my_fab) )
    }
    )
}
@Composable
fun MyProgress(){
    LinearProgressIndicator()
    CircularProgressIndicator(
        color = colorResource(id = R.color.teal_700),
        strokeWidth = 5.dp,
    )
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FootballTheme {
        Greeting("Android")
    }
}