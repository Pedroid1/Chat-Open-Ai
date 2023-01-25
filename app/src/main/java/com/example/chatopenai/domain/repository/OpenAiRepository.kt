package com.example.chatopenai.domain.repository

import com.example.chatopenai.data.remote.model.CompletionRequest
import com.example.chatopenai.data.remote.model.GenerateImageRequest
import com.example.chatopenai.domain.model.MessageResponse
import com.example.chatopenai.domain.model.UrlResponse
import com.example.chatopenai.util.Resource

interface OpenAiRepository {
    suspend fun completeText(completionRequest: CompletionRequest): Resource<List<MessageResponse>>
    suspend fun generateImage(generateImageRequest: GenerateImageRequest): Resource<List<UrlResponse>>
}