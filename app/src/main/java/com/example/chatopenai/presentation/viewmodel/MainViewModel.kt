package com.example.chatopenai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatopenai.domain.repository.OpenAiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: OpenAiRepository
) : ViewModel() {

}