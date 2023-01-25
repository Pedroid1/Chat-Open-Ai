package com.example.chatopenai.data.repository

import com.example.chatopenai.data.remote.OpenAiApi
import com.example.chatopenai.data.remote.model.CompletionRequest
import com.example.chatopenai.data.remote.model.GenerateImageRequest
import com.example.chatopenai.data.remote.model.toListOfMessageResponse
import com.example.chatopenai.data.remote.model.toListOfUrlResponse
import com.example.chatopenai.domain.model.MessageResponse
import com.example.chatopenai.domain.model.UrlResponse
import com.example.chatopenai.domain.repository.OpenAiRepository
import com.example.chatopenai.util.ApiKey
import com.example.chatopenai.util.Resource
import javax.inject.Inject

class OpenAiRepositoryImpl @Inject constructor(
    private val api: OpenAiApi
) : OpenAiRepository {

    override suspend fun completeText(completionRequest: CompletionRequest): Resource<List<MessageResponse>> {
        val result = try {
            val response = api.complete(
                ApiKey.API_KEY,
                ApiKey.ORGANIZATION_KEY,
                completionRequest
            )
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.toListOfMessageResponse()
            } else {
                return Resource.Error("An unknown error occured")
            }
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(result)
    }

    override suspend fun generateImage(generateImageRequest: GenerateImageRequest): Resource<List<UrlResponse>> {
        val response = try {
            val result = api.generateImage(
                ApiKey.API_KEY,
                generateImageRequest
            )
            if (result.isSuccessful && result.body() != null) {
                result.body()!!.toListOfUrlResponse()
            } else {
                return Resource.Error("An unknown error occured")
            }
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }
}