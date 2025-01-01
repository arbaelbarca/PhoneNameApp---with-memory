package org.arba.project

class ContactModel(
    var idContact: Int? = 0,
    var nameContact: String? = "",
    var phoneContact: String? = ""
)

val listDummyContactModel = listOf(
    ContactModel(0,"Arba","08838212312"),
    ContactModel(0,"User3","0844188383"),
    ContactModel(0,"User4","098488123"),
    ContactModel(0,"User5","00889918923")
)