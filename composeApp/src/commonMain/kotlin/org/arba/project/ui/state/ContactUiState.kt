package org.arba.project.ui.state

sealed class ContactUiState<out T> {
    data class Loading<out T>(val data: T) : ContactUiState<T>()
    data class Success<out T>(val data: T) : ContactUiState<T>()

    data class EmptyData(val empty: String) : ContactUiState<Nothing>()
    data class Error(val exception: Throwable) : ContactUiState<Nothing>()
}