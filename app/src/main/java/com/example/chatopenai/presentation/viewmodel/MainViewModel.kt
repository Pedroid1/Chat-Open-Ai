package com.example.chatopenai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatopenai.domain.use_case.ApiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases
) : ViewModel() {

}