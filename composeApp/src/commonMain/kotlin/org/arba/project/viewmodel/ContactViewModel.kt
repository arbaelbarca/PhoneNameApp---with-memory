package org.arba.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.arba.project.data.ContactUiState

class ContactViewModel : ViewModel() {
    val contactList = MutableStateFlow(ContactUiState.Success(emptyList()))
    private val emptyData = MutableStateFlow(ContactUiState.EmptyData(""))
    var uiState: StateFlow<ContactUiState> = contactList

    fun getContactList() {
        viewModelScope.launch {
            ContactManager.getAllContactList().onEach { list ->
                if (list.isNotEmpty()) {
                    contactList.value = ContactUiState.Success(list)
                } else {
                    emptyData.value = ContactUiState.EmptyData("")
                }
            }.collect()
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