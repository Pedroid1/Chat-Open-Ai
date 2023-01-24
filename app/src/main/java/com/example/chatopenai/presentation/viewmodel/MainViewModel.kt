package com.example.chatopenai.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatopenai.data.remote.model.GenerateImageRequest
import com.example.chatopenai.domain.repository.OpenAiRepository
import com.example.chatopenai.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: OpenAiRepository
) : ViewModel() {

    val url = MutableLiveData<String?>(null)

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}