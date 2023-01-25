package com.example.chatopenai.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerateImageRequest(
    val prompt: String,
    @SerializedName("n")
    val numberOfGenerateImages: Int = 1,
    val size: String = "1024x1024"
)
