package com.danshima.flickrsearch.network

import com.danshima.flickrsearch.model.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=dogs")
    suspend fun fetchPhotos(@Query("api_key") apiKey: String): PhotosSearchResponse
}

data class PhotosSearchResponse(
    val photos: ImagesResponse
)