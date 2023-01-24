package com.example.chatopenai.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerateImageResponse(
    @SerializedName("data")
    val images: List<ImageUrl>
) {
    fun convertResponseInStringList(): List<String> {
        val responseList = ArrayList<String>()
        for (img in this.images) {
            responseList.add(img.url)
        }
        return responseList
    }
}