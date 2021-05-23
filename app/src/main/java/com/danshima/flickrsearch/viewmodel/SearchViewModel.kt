package com.danshima.flickrsearch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danshima.flickrsearch.model.Image
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.danshima.flickrsearch.repository.SearchRepository

class SearchViewModel: ViewModel() {
    private val repository = SearchRepository()
    var images: List<Image> by mutableStateOf(listOf())
        private set

    init {
        fetchImages("okonomiyaki")
    }

    fun fetchImages(searchInput: String) {
        viewModelScope.launch {
            runCatching {
                repository.fetchImages(searchInput)
            }.onSuccess { searchResponse ->
                val photosList = searchResponse.photos.photo.map { photo ->
                    val title = if (photo.title.isEmpty()) "No image name" else photo.title
                    Image(
                        id = photo.id,
                        url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                        title = title
                    )
                }
                images = photosList
            }.onFailure { e ->
                Log.d("ViewModel:Fetch Images", e.toString())
            }
        }
    }
}