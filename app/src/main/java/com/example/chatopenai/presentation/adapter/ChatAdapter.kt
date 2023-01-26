package com.example.chatopenai.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.chatopenai.databinding.ChatMessageResponseBinding
import com.example.chatopenai.databinding.ChatPromptBinding
import com.example.chatopenai.databinding.ChatUrlResponseBinding
import com.example.chatopenai.presentation.enuns.EnumChatRecyclerViewType
import com.example.chatopenai.presentation.model.ChatRecyclerViewItem
import com.example.chatopenai.presentation.viewholder.ChatViewHolder

class ChatAdapter : ListAdapter<ChatRecyclerViewItem, ChatViewHolder>(DIFFUTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return when (EnumChatRecyclerViewType.getEnumByOrdinal(viewType)) {
            EnumChatRecyclerViewType.PROMPT_VIEW_TYPE -> {
                return ChatViewHolder.Prompt(
                    ChatPromptBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            EnumChatRecyclerViewType.MESSAGE_RESPONSE_VIEW_TYPE -> {
                return ChatViewHolder.MessageResponse(
                    ChatMessageResponseBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            EnumChatRecyclerViewType.URL_RESPONSE_VIEW_TYPE -> {
                return ChatViewHolder.UrlResponse(
                    ChatUrlResponseBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
            EnumChatRecyclerViewType.ERROR_RESPONSE_VIEW_TYPE -> {
                return ChatViewHolder.ErrorResponse(
                    ChatMessageResponseBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) = when (holder) {
        is ChatViewHolder.Prompt -> holder.bindPromptMessage(getItem(position) as ChatRecyclerViewItem.PromptItem)
        is ChatViewHolder.MessageResponse -> holder.bindResponseMessage(getItem(position) as ChatRecyclerViewItem.MessageResponseItem)
        is ChatViewHolder.UrlResponse -> holder.bindUrl(getItem(position) as ChatRecyclerViewItem.UrlResponseItem)
        is ChatViewHolder.ErrorResponse -> holder.bindError(getItem(position) as ChatRecyclerViewItem.ErrorResponseItem)
    }
    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }
    companion object {
        val DIFFUTILS = object : DiffUtil.ItemCallback<ChatRecyclerViewItem>() {
            override fun areItemsTheSame(
                oldItem: ChatRecyclerViewItem,
                newItem: ChatRecyclerViewItem
            ): Boolean {
                return when {
                    oldItem is ChatRecyclerViewItem.PromptItem && newItem is ChatRecyclerViewItem.PromptItem -> {
                        oldItem.promptItem == newItem.promptItem
                    }
                    oldItem is ChatRecyclerViewItem.MessageResponseItem && newItem is ChatRecyclerViewItem.MessageResponseItem -> {
                        oldItem.messageItem == newItem.messageItem
                    }
                    oldItem is ChatRecyclerViewItem.UrlResponseItem && newItem is ChatRecyclerViewItem.UrlResponseItem -> {
                        oldItem.urlItem == newItem.urlItem
                    }
                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: ChatRecyclerViewItem,
                newItem: ChatRecyclerViewItem
            ): Boolean {
                return when {
                    oldItem is ChatRecyclerViewItem.PromptItem && newItem is ChatRecyclerViewItem.PromptItem -> {
                        oldItem.promptItem == newItem.promptItem
                    }
                    oldItem is ChatRecyclerViewItem.MessageResponseItem && newItem is ChatRecyclerViewItem.MessageResponseItem -> {
                        oldItem.messageItem == newItem.messageItem
                    }
                    oldItem is ChatRecyclerViewItem.UrlResponseItem && newItem is ChatRecyclerViewItem.UrlResponseItem -> {
                        oldItem.urlItem == newItem.urlItem
                    }
                    else -> false
                }
            }
        }
    }
}