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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
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


    @ExperimentalAnimationApi
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
            backgroundColor = Color.White
        )
    }

    @Composable
    fun SearchBar(
        viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ) {
        var searchText by remember { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .requiredHeight(56.dp),
            value = searchText,
            leadingIcon = {
                Icon(
                    modifier = Modifier.requiredSize(18.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.search_input_hint)
                )
            },
            onValueChange = {
                searchText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { viewModel.fetchImages(searchText) }
            ),
            label = {
                Text(
                    stringResource(id = R.string.search_input_hint),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                unfocusedIndicatorColor = MaterialTheme.colors.onSurface
            ),
            shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
        )
    }



    @ExperimentalAnimationApi
    @Composable
    fun ShowImageList(
        viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    ) {
        val listState = rememberLazyListState()
        @OptIn(ExperimentalFoundationApi::class)
        LazyColumn(state = listState, contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp), modifier = Modifier.padding(top = 16.dp)) {
            items(
                items = viewModel.images,
                itemContent = {
                    com.danshima.flickrsearch.view.ListItem(item = it)
                }
            )
        }
    }

    @ExperimentalAnimationApi
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