package com.danshima.flickrsearch.network

import com.danshima.flickrsearch.model.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun fetchPhotos(
        @Query("api_key") apiKey: String,
        @Query("text") searchText: String
    ): PhotosSearchResponse
}

data class PhotosSearchResponse(
    val photos: ImagesResponse
)