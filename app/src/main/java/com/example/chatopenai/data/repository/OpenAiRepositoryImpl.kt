package com.example.chatopenai.data.repository

import com.example.chatopenai.data.remote.OpenAiApi
import com.example.chatopenai.domain.model.Completion
import com.example.chatopenai.domain.model.CompletionRequest
import com.example.chatopenai.domain.repository.OpenAiRepository
import com.example.chatopenai.util.ApiKey
import com.example.chatopenai.util.Resource
import javax.inject.Inject

class OpenAiRepositoryImpl @Inject constructor(
    private val api: OpenAiApi
) : OpenAiRepository {


    override suspend fun completeText(completionRequest: CompletionRequest): Resource<Completion> {
        val result = try {
            val response = api.complete(
                ApiKey.API_KEY,
                ApiKey.ORGANIZATION_KEY,
                completionRequest
            )
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.completions.first()
            } else {
                return Resource.Error("An unknown error occured")
            }
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(result)
    }
}