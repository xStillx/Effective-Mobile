package com.arslan.myapplication.utils

sealed class ViewState<out T> {
    data class Show<T>(val data: T) : ViewState<T>()
    data object Empty : ViewState<Nothing>()
    data class Error(val throwable: Throwable) : ViewState<Nothing>()
}

fun <T> RequestResult<T>.asViewState(): ViewState<T> = when (this) {
    is RequestResult.Failed.Error -> ViewState.Error(throwable)
    is RequestResult.Success -> when {
        data == null -> ViewState.Empty
        data is Collection<*> && data.isEmpty() -> ViewState.Empty
        else -> ViewState.Show(data)
    }
}