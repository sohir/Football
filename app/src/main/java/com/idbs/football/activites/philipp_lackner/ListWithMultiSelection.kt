package com.idbs.football.activites.philipp_lackner

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.idbs.football.activites.philipp_lackner.ui.theme.FootballTheme

class ListWithMultiSelection : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballTheme {
              var items by remember {
                  mutableStateOf(
                      (1..20).map {
                          ListItemModel(title = "item $it", isSelected = false)
                      }
                  )
              }
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(items.size){i ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    items = items.mapIndexed { index, item ->
                                        if (i == index){
                                            item.copy(isSelected = !item.isSelected)
                                        }else item
                                    }
                                }
                                .padding(16.dp)
                        , horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = items[i].title)
                            if (items[i].isSelected){
                                Icon(imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = androidx.compose.ui.graphics.Color.Blue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

