package com.danshima.flickrsearch.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.danshima.flickrsearch.model.Image
import com.google.accompanist.glide.rememberGlidePainter


@ExperimentalAnimationApi
@Composable
fun ListItem(item: Image) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        var expanded by remember { mutableStateOf(false) }
        Row(Modifier.clickable { expanded = !expanded }) {
            val imageSize = if (expanded) Modifier
                .padding(8.dp)
                .size(120.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            else
                Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            LoadImage(item, imageSize)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = item.title, style = MaterialTheme.typography.h6)
                AnimatedVisibility(visible = expanded) {
                    Text(
                        text = "nice eh?",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadImage(item: Image, modifier: Modifier) {
    Image(
        painter = rememberGlidePainter(item.url, fadeIn = true),
        contentDescription = item.title,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
}


