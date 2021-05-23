package com.danshima.flickrsearch.model


data class ImageEntity(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)
