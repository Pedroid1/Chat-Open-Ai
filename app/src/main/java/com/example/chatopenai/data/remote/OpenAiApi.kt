package com.example.chatopenai.data.remote

import com.example.chatopenai.domain.model.CompletionRequest
import com.example.chatopenai.domain.model.CompletionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {

    @POST("engines/text-davinci-003/completions")
    @Headers("Content-Type: application/json")
    suspend fun complete(
        @Header("Authorization") apiKey: String,
        @Header("OpenAI-Organization") organizationKey: String,
        @Body request: CompletionRequest
    ): Response<CompletionResponse>

}