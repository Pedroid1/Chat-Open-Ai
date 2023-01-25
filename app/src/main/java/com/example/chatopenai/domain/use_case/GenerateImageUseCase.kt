package com.example.chatopenai.domain.use_case

import com.example.chatopenai.data.remote.model.GenerateImageRequest
import com.example.chatopenai.domain.repository.OpenAiRepository

class GenerateImageUseCase(
    private val repository: OpenAiRepository
) {
    suspend operator fun invoke(generateImageRequest: GenerateImageRequest) =
        repository.generateImage(generateImageRequest)
}