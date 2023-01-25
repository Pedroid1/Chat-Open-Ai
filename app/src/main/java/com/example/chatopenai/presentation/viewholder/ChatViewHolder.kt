package com.example.chatopenai.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.chatopenai.common.loadImage
import com.example.chatopenai.common.toVisible
import com.example.chatopenai.databinding.ChatMessageResponseBinding
import com.example.chatopenai.databinding.ChatPromptBinding
import com.example.chatopenai.databinding.ChatUrlResponseBinding
import com.example.chatopenai.presentation.model.ChatRecyclerViewItem

sealed class ChatViewHolder(_binding: ViewBinding) : RecyclerView.ViewHolder(_binding.root) {

    class Prompt(
        private val _binding: ChatPromptBinding
    ) : ChatViewHolder(_binding) {

        fun bindPromptMessage(prompt: ChatRecyclerViewItem.PromptItem) {
            _binding.promptTxt.text = prompt.promptItem.message
        }
    }

    class MessageResponse(
        private val _binding: ChatMessageResponseBinding
    ) : ChatViewHolder(_binding) {

        fun bindResponseMessage(messageResponse: ChatRecyclerViewItem.MessageResponseItem) {
            _binding.responseTxt.text = messageResponse.messageItem.message
        }
    }

    class UrlResponse(
        private val _binding: ChatUrlResponseBinding
    ) : ChatViewHolder(_binding) {

        fun bindUrl(urlResponse: ChatRecyclerViewItem.UrlResponseItem) {
            _binding.img.toVisible()
            _binding.img.loadImage(urlResponse.urlItem.url)
        }
    }
}