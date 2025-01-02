package org.arba.project.data

import org.arba.project.ContactModel

sealed class ContactUiState<out T> {
    object Loading : ContactUiState<Nothing>()
    data class Success<out T>(val data: T) : ContactUiState<T>()

    //    data class Success(val news: List<ContactModel>) : ContactUiState<ContactModel?>()
//    object EmptyData : ContactUiState<Nothing>()
    data class EmptyData(val exception: String) : ContactUiState<Nothing>()
    data class Error(val exception: Throwable) : ContactUiState<Nothing>()
//    data class Error(val exception: Throwable) : ContactUiState<ContactModel?>()
}