package com.example.chatopenai.data.remote.model

import com.example.chatopenai.domain.model.MessageResponse
import com.google.gson.annotations.SerializedName

data class CompletionResponse(
    @SerializedName("choices") val completions: List<Completion>
)
fun CompletionResponse.toListOfMessageResponse(): List<MessageResponse> {
    return completions.map {
        it.toMessageResponse()
    }
}