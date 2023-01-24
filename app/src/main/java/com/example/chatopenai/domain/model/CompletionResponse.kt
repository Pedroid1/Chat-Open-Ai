package com.example.chatopenai.domain.model

import com.google.gson.annotations.SerializedName

data class CompletionResponse(
    val id: String, @SerializedName("choices") val completions: List<Completion>
)