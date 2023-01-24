package com.example.chatopenai.domain.model

import com.google.gson.annotations.SerializedName

data class CompletionRequest(
    val prompt: String,
    val temperature: Float? = null,
    @SerializedName("max_tokens")
    val maxLength: Int? = null
)
