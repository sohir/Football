package com.idbs.football

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.idbs.football.ui.theme.FootballTheme

class ListComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                 //   LazyList()
                    ConstraintLayoutExample()
                }
         /*       MyColumn(modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()))
            //.horizontalScroll(rememberScrollState())) for the row and H scrolling
*/
            }
        }
    }
}
@Composable
fun ConstraintLayoutExample(){
    val constraints = ConstraintSet{
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("readBox")
        val guideline = createGuidelineFromTop(0.5f)
        constrain(greenBox){
           // top.linkTo(parent.top)
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox){
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        createHorizontalChain(greenBox,redBox, chainStyle = ChainStyle.Packed)
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .layoutId("greenBox")
            .background(Color.Gray))
        Box(modifier = Modifier
            .layoutId("readBox")
            .background(Color.Green))

    }
}
@Composable
fun LazyList(){
   // val scrollState = rememberScrollState()
    LazyColumn {
     itemsIndexed(
         listOf("This","is","Jetpack","Compose")
     ){ index, item ->
         Text(text = "Item $item with index $index",
             fontSize = 24.sp,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Center,
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(vertical = 24.dp)
         )
     }
        //We can use both items :D just WOW!!!
        items(5000){
            Text(text = "Item $it",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
        }
    }
}

@Composable
fun MyColumn(modifier: Modifier){
    Column(modifier = modifier) {
        Text(text = "From Simple English Wikipedia, the free encyclopediaJump to navigationJump to searchThis fragment of a 5th-century Bible shows the text was paragraphedA symbol sometimes used to show where a paragraph beginsA paragraph is a collection of words strung together to make a longer unit than a sentence. Several sentences often make to a paragraph. There are normally three to eight sentences in a paragraph. Paragraphs can start with a five-space indentation or by skipping a line and then starting over. This makes it simpler to tell when one paragraph ends and the next starts.simply it has 3-9 linesA topic phrase appears in most ordered types of writing, such as essays. This paragraph's topic sentence informs the reader about the topic of the paragraph. In most essays, numerous paragraphs make statements to support a thesis statement, which is the essay's fundamental point.Paragraphs may signal when the writer changes topics. Each paragraph may have a number of sentences, depending on the topic.A pilcrow mark (¶) is sometimes used to show where a paragraph begins.", fontSize = 22.sp,
            color = Color.Black)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FootballTheme {
    }
}