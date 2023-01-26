package com.example.chatopenai.presentation.model

import com.example.chatopenai.domain.model.MessageResponse
import com.example.chatopenai.domain.model.Prompt
import com.example.chatopenai.domain.model.UrlResponse
import com.example.chatopenai.presentation.enuns.EnumChatRecyclerViewType

sealed class ChatRecyclerViewItem(val type: EnumChatRecyclerViewType) {

    class PromptItem(val promptItem: Prompt) : ChatRecyclerViewItem(EnumChatRecyclerViewType.PROMPT_VIEW_TYPE)

    class MessageResponseItem(val messageItem: MessageResponse) : ChatRecyclerViewItem(EnumChatRecyclerViewType.MESSAGE_RESPONSE_VIEW_TYPE)

    class UrlResponseItem(val urlItem: UrlResponse) : ChatRecyclerViewItem(EnumChatRecyclerViewType.URL_RESPONSE_VIEW_TYPE)

    class ErrorResponseItem(val errorItem: Error) : ChatRecyclerViewItem(EnumChatRecyclerViewType.ERROR_RESPONSE_VIEW_TYPE)
}
