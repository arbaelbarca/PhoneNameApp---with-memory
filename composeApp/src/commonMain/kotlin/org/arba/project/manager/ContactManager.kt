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

//    fun addContactItem(nameContact: String, phoneContact: String) {
//        contactList.add(ContactModel(0, nameContact, phoneContact))
//    }

    fun deleteContactItem(contactModel: ContactModel) {
//        contactList.remove(id)
        _contactList.value = _contactList.value.filterNot { it == contactModel }

    }

}