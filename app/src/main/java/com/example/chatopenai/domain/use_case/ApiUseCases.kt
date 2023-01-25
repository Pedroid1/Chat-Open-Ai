package com.example.chatopenai.domain.use_case

data class ApiUseCases(
    val generateCompletionUseCase: GenerateCompletionUseCase,
    val generateImageUseCase: GenerateImageUseCase
)
