package com.example.chatopenai.presentation.enuns

enum class EnumChatRecyclerViewType {
    PROMPT_VIEW_TYPE,
    MESSAGE_RESPONSE_VIEW_TYPE,
    URL_RESPONSE_VIEW_TYPE;

    companion object {
        fun getEnumByOrdinal(index: Int) : EnumChatRecyclerViewType {
            return values()[index]
        }
    }
}