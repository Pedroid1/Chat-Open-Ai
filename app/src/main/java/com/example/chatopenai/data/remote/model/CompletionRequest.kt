package com.example.chatopenai.data.remote.model

import com.google.gson.annotations.SerializedName

data class CompletionRequest(
    val model: String = "text-davinci-003",
    val prompt: String,
    @SerializedName("max_tokens")
    val maxTokens: Int? = null,
    val temperature: Float = 0f,
    @SerializedName("top_p")
    val topP: Int = 1,
    @SerializedName("n")
    val numberOfCompletions: Int = 1,
    val stream: Boolean = false,
    val logprobs: Int? = null,
    val stop: String? = null
)
