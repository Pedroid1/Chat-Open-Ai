package com.example.chatopenai.data.remote

import com.example.chatopenai.data.remote.model.CompletionRequest
import com.example.chatopenai.data.remote.model.CompletionResponse
import com.example.chatopenai.data.remote.model.GenerateImageRequest
import com.example.chatopenai.data.remote.model.GenerateImageResponse
import retrofit2.Response
import retrofit2.http.*

interface OpenAiApi {

    @POST("completions")
    @Headers("Content-Type: application/json")
    suspend fun complete(
        @Header("Authorization") apiKey: String,
        @Header("OpenAI-Organization") organizationKey: String,
        @Body request: CompletionRequest
    ): Response<CompletionResponse>

    @POST("images/generations")
    @Headers("Content-Type: application/json")
    suspend fun generateImage(
        @Header("Authorization") apiKey: String,
        @Body request: GenerateImageRequest
    ): Response<GenerateImageResponse>

}