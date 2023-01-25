package com.example.chatopenai.data.remote.model

import com.example.chatopenai.domain.model.MessageResponse
import com.example.chatopenai.domain.model.UrlResponse

data class ImageUrl(
    val url: String
)

fun ImageUrl.toUrlResponse() = UrlResponse(
    url = this.url
)
