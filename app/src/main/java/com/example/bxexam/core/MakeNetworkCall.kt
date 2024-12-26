package com.example.bxexam.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): UiState<T> = withContext(Dispatchers.IO){
    try {
        UiState.Success(call())
    }catch (exception:Exception){
        UiState.Error(exception)
    }
}
