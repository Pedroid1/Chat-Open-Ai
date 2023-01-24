package com.example.chatopenai.domain.repository

import com.example.chatopenai.domain.model.Completion
import com.example.chatopenai.domain.model.CompletionRequest
import com.example.chatopenai.util.Resource

interface OpenAiRepository {
    suspend fun completeText(completionRequest: CompletionRequest): Resource<Completion>
}