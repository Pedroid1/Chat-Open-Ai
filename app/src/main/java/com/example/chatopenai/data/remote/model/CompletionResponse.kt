package com.example.chatopenai.data.remote.model

import com.google.gson.annotations.SerializedName

data class CompletionResponse(
    @SerializedName("choices") val completions: List<Completion>
) {
    fun convertResponseInStringList() : List<String> {
         val responseList = ArrayList<String>()
        for(completions in this.completions) {
            responseList.add(completions.text)
        }
        return responseList
    }
}