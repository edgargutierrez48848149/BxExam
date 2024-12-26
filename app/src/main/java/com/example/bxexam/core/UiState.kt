package com.example.bxexam.core

sealed class UiState<T> {

    class Loading<T>: UiState <T>()

    class Success<T>(val data:T):UiState<T>()

    class Error<T>(val error:Exception):UiState<T>()
}