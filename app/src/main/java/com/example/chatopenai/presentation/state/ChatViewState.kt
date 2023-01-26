package com.example.chatopenai.presentation.state

import com.example.chatopenai.presentation.model.ChatRecyclerViewItem

data class ChatViewState(
    val lastRequestIsCompleted: Boolean? = null,
    val chatMessageList: List<ChatRecyclerViewItem> = emptyList()
)