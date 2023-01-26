package com.example.chatopenai.di

import com.example.chatopenai.data.remote.OpenAiApi
import com.example.chatopenai.data.repository.OpenAiRepositoryImpl
import com.example.chatopenai.domain.repository.OpenAiRepository
import com.example.chatopenai.domain.use_case.ApiUseCases
import com.example.chatopenai.domain.use_case.GenerateCompletionUseCase
import com.example.chatopenai.domain.use_case.GenerateImageUseCase
import com.example.chatopenai.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideChatApi(): OpenAiApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(OpenAiApi::class.java)
    }

    @Provides
    @Singleton
    fun provideChatRepository(api: OpenAiApi): OpenAiRepository {
        return OpenAiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideApiUseCases(
        repo: OpenAiRepository
    ) = ApiUseCases(
        generateCompletionUseCase = GenerateCompletionUseCase(repo),
        generateImageUseCase = GenerateImageUseCase(repo)
    )

}