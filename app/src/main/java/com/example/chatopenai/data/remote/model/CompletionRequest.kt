package com.example.chatopenai.data.remote.model

import com.google.gson.annotations.SerializedName

data class CompletionRequest(
    val prompt: String,
    val temperature: Float? = null,
    @SerializedName("max_tokens")
    val maxTokens: Int? = null
)
