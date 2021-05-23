package com.danshima.flickrsearch.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.danshima.flickrsearch.viewmodel.SearchViewModel
import com.danshima.flickrsearch.R
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchBar(
    viewModel: SearchViewModel = viewModel()
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
