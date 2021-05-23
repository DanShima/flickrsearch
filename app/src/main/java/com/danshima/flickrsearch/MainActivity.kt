package com.danshima.flickrsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.danshima.flickrsearch.ui.theme.FlickrSearchTheme
import com.danshima.flickrsearch.viewmodel.SearchViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danshima.flickrsearch.view.SearchBar
import com.danshima.flickrsearch.view.ListItem
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrSearchTheme {
                Surface {
                    Overview()
                }
            }
        }
    }


    @ExperimentalAnimationApi
    @Preview(showBackground = true)
    @Composable
    fun Overview() {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp, bottom = 16.dp)
                )  {

                    SearchBar()
                    ShowImageList()
                }

            },
            backgroundColor = MaterialTheme.colors.onSecondary
        )
    }

    @ExperimentalAnimationApi
    @Composable
    fun ShowImageList(
        viewModel: SearchViewModel = viewModel()
    ) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState, contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), modifier = Modifier.padding(top = 16.dp)) {
            items(
                items = viewModel.images,
                itemContent = {
                    ListItem(item = it)
                }
            )
        }
    }
}