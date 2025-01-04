package org.arba.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


object ContactManager {
//    val contactList: MutableList<ContactModel> = mutableListOf()

//    fun getAllContactList(): Flow<Result<MutableList<ContactModel>>> =
//        flow {
//            kotlin.runCatching {
//                emit(Result.success(contactList))
//            }.onFailure {
//                emit(Result.failure(it))
//                it.printStackTrace()
//            }
//        }.flowOn(Dispatchers.Main)
//            .catch {
//                emit(Result.failure(it))
//            }

    private val _contactList = MutableStateFlow<List<ContactModel>>(emptyList())
    val contactList: StateFlow<List<ContactModel>> = _contactList.asStateFlow()

    fun getAllContactList() = contactList

    fun addContactItem(name: String, phone: String) {
        val newContact = ContactModel(nameContact = name, phoneContact = phone)
        _contactList.value += newContact // Tambahkan kontak baru
    }

    fun updateContactItem(idContact: Int, name: String, phone: String) {
        _contactList.value = _contactList.value.map { contact ->
            if (contact.idContact == idContact) {
                contact.copy(idContact, name, phone) // Update item yang sesuai
            } else {
                contact // Tetap sama untuk item lainnya
            }
        }
    }

    fun deleteContactItem(contactModel: ContactModel) {
        _contactList.value = _contactList.value.filterNot { it == contactModel }

    }

}