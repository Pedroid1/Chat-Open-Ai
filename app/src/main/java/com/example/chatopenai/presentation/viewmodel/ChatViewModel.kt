package com.example.chatopenai.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatopenai.data.remote.model.CompletionRequest
import com.example.chatopenai.data.remote.model.GenerateImageRequest
import com.example.chatopenai.domain.model.Prompt
import com.example.chatopenai.domain.use_case.ApiUseCases
import com.example.chatopenai.presentation.enuns.RequestType
import com.example.chatopenai.presentation.model.ChatRecyclerViewItem
import com.example.chatopenai.presentation.model.Error
import com.example.chatopenai.presentation.state.ChatViewState
import com.example.chatopenai.presentation.util.UiText
import com.example.chatopenai.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases
) : ViewModel() {
    var requestType: RequestType = RequestType.GENERATE_TEXT
    private val chatMessageList: MutableList<ChatRecyclerViewItem> = ArrayList()

    private val _chatViewState = MutableLiveData(ChatViewState())
    val chatViewState get() = _chatViewState

    private fun addItemChatList(item: ChatRecyclerViewItem) {
        chatMessageList.add(item)

        if (item is ChatRecyclerViewItem.PromptItem) {
            _chatViewState.postValue(
                _chatViewState.value?.copy(
                    chatMessageList = chatMessageList.toList(),
                    lastRequestIsCompleted = false
                )
            )
        } else {
            _chatViewState.postValue(
                _chatViewState.value?.copy(
                    chatMessageList = chatMessageList.toList(),
                    lastRequestIsCompleted = true
                )
            )
        }

    }

    fun makeRequest(text: String) {
        when(requestType) {
            RequestType.GENERATE_TEXT -> makeCompletionRequest(text)
            RequestType.GENERATE_IMAGE -> makeGenerateImageRequest(text)
        }
    }

    private fun makeCompletionRequest(text: String) {
        addItemChatList(ChatRecyclerViewItem.PromptItem(promptItem = Prompt(text)))

        viewModelScope.launch(Dispatchers.IO) {
            val completionRequest = CompletionRequest(prompt = text)
            when (val response = apiUseCases.generateCompletionUseCase(completionRequest)) {
                is Resource.Success -> {
                    launch(Dispatchers.Main) {
                        response.data?.firstOrNull()?.apply {
                            addItemChatList(ChatRecyclerViewItem.MessageResponseItem(this))
                        }
                    }
                }
                is Resource.Error -> {
                    launch(Dispatchers.Main) {
                        response.message?.apply {
                            addItemChatList(
                                ChatRecyclerViewItem.ErrorResponseItem(
                                    Error(UiText.DynamicString(response.message))
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun makeGenerateImageRequest(text: String) {
        addItemChatList(ChatRecyclerViewItem.PromptItem(promptItem = Prompt(text)))

        viewModelScope.launch(Dispatchers.IO) {
            val generateImageRequest = GenerateImageRequest(prompt = text)
            when (val response = apiUseCases.generateImageUseCase(generateImageRequest)) {
                is Resource.Success -> {
                    launch(Dispatchers.Main) {
                        response.data?.firstOrNull()?.apply {
                            addItemChatList(ChatRecyclerViewItem.UrlResponseItem(this))
                        }
                    }
                }
                is Resource.Error -> {
                    launch(Dispatchers.Main) {
                        response.message?.apply {
                            addItemChatList(
                                ChatRecyclerViewItem.ErrorResponseItem(
                                    Error(UiText.DynamicString(response.message))
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}