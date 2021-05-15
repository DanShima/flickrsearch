package com.danshima.flickrsearch.model


data class ImagesResponse(
    val page: Int,
    val photo: List<ImageEntity>
)