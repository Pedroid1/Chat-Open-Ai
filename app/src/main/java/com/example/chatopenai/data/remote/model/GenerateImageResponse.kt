package com.example.chatopenai.data.remote.model

import com.example.chatopenai.domain.model.UrlResponse
import com.google.gson.annotations.SerializedName

data class GenerateImageResponse(
    @SerializedName("data")
    val images: List<ImageUrl>
)
fun GenerateImageResponse.toListOfUrlResponse(): List<UrlResponse> {
    return images.map {
        it.toUrlResponse()
    }
}