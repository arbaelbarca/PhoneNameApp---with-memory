package org.arba.project.data

import org.arba.project.ContactModel

sealed class ContactUiState {
    object Loading : ContactUiState()
    data class Success(val news: List<ContactModel>) : ContactUiState()
    data class Error(val exception: Throwable) : ContactUiState()
    data class EmptyData(val string: String) : ContactUiState()
}