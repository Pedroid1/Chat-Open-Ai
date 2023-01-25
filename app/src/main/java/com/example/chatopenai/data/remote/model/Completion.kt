package com.example.chatopenai.data.remote.model

import com.example.chatopenai.domain.model.MessageResponse

data class Completion(val text: String)

fun Completion.toMessageResponse() = MessageResponse(
    message = text
)