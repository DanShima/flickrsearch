package com.danshima.flickrsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.danshima.flickrsearch.ui.theme.FlickrSearchTheme
import com.danshima.flickrsearch.viewmodel.SearchViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DefaultPreview()
                }
            }
        }
    }


@Composable
fun Overview() {
    Scaffold(
        content = {
            ShowImageList()
        },
        backgroundColor = Color.White
    )
}


@Composable
fun ShowImageList(
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val listState = rememberLazyListState()
    @OptIn(ExperimentalFoundationApi::class)
    LazyColumn(state = listState, contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(
            items = viewModel.images,
            itemContent = {
                ListItem(item = it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlickrSearchTheme {
        Surface(color = MaterialTheme.colors.background) {
            Overview()
        }
    }
}
}