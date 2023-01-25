package com.example.chatopenai.domain.use_case

import com.example.chatopenai.data.remote.model.CompletionRequest
import com.example.chatopenai.domain.repository.OpenAiRepository

class GenerateCompletionUseCase(
    private val repository: OpenAiRepository
) {
    suspend operator fun invoke(completionRequest: CompletionRequest) = repository.completeText(completionRequest)
}