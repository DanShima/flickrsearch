package com.danshima.flickrsearch.repository

import com.danshima.flickrsearch.BuildConfig
import com.danshima.flickrsearch.network.SearchApiService

class SearchRepository {
    private val api = SearchApiService.searchService
    suspend fun fetchImages(searchInput: String) = api.fetchPhotos(BuildConfig.API_KEY, searchInput)
}