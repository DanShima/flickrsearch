package com.danshima.flickrsearch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danshima.flickrsearch.BuildConfig
import com.danshima.flickrsearch.model.Image
import com.danshima.flickrsearch.network.SearchApiService
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SearchViewModel : ViewModel() {
    private val api = SearchApiService.searchService
    var images: List<Image> by mutableStateOf(listOf())
        private set

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            try {
                val searchResponse = api.fetchPhotos(BuildConfig.API_KEY)
                val photosList = searchResponse.photos.photo.map { photo ->
                    Image(
                        id = photo.id,
                        url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                        title = photo.title
                    )
                }

                images = photosList
                Log.d("images", "images $photosList ${images.size}")
            } catch (e: Exception) {
                Log.d("ViewModel:Fetch Images", e.toString())
            }
        }
    }
}