package org.arba.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.arba.project.data.ContactUiState

class ContactViewModel : ViewModel() {
    val contactList = MutableStateFlow<ContactUiState<List<ContactModel>>>(ContactUiState.Loading(emptyList()))
    var contactUiState: StateFlow<ContactUiState<List<ContactModel>>> = contactList

    fun getContactList() {
        viewModelScope.launch {
            try {
                contactList.value = ContactUiState.Loading(emptyList())
                ContactManager.getAllContactList().onEach { list ->
                    if (list.isNotEmpty()) {
                        contactList.value = ContactUiState.Loading(list)
                        contactList.value = ContactUiState.Success(list)
                    } else {
                        contactList.value = ContactUiState.EmptyData("Empty Data")
                    }
                }.collect()
            } catch (e: Exception) {
                contactList.value = ContactUiState.Error(e)
            }

        }
//        contactList.value = ContactManager.getAllContactList()
    }

    fun addContact(contactModel: ContactModel) {
        ContactManager.addContactItem(
            contactModel.nameContact.toString(),
            contactModel.phoneContact.toString()
        )
        getContactList()
    }

    fun deleteContactItem(contactModel: ContactModel) {
        ContactManager.deleteContactItem(contactModel)
        getContactList()
    }
}