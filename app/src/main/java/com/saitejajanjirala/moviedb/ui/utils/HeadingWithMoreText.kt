package com.saitejajanjirala.moviedb.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HeadingWithMoreText(tag:String,onClicked:()->Unit){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(text = tag, style = MaterialTheme.typography.titleMedium)

        Box(Modifier.clickable {
            onClicked()
        }){
            Text(text = "More..", style = MaterialTheme.typography.titleMedium,
                color = Color.Blue)
        }
    }
}