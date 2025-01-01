package org.arba.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.arba.project.data.ContactUiState
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainContactPage(contactViewModel: ContactViewModel) {

//    val listDummyContact by contactViewModel.contactList.observeAsState()

    val contactState by contactViewModel.contactList.collectAsState()

    var inputName by remember {
        mutableStateOf("")
    }

    var inputPhone by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contact Data") },
                backgroundColor = Color.White
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Column(
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    placeholder = { Text("Input Name") },
                    value = inputName,
                    onValueChange = {
                        inputName = it
                    })

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .padding(end = 10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = { Text("Input Phone") },
                    value = inputPhone,
                    onValueChange = {
                        inputPhone = it
                    })

                Spacer(
                    modifier = Modifier.padding(top = 20.dp)
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    onClick = {
                        contactViewModel.addContact(ContactModel(0, inputName, inputPhone))
                        inputName = ""
                        inputPhone = ""
                    }) {
                    Text(
                        "Add Contact",
                        fontSize = 13.sp,
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }


//        when (contactState) {
//            is ContactUiState.Success -> {
//
//            }
//
//            is ContactUiState.EmptyData -> {
//
//            }
//
//            is ContactUiState.Error -> {
//
//            }
//        }
            when (val state = contactViewModel.uiState.collectAsState().value) {
                is ContactUiState.Error -> {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        textAlign = TextAlign.Center,
                        text = "Error data Empty",
                    )
                }

                is ContactUiState.Success -> {
                    val dataList = state.news
                    println("respon Json List $dataList")
                    LazyColumn {
                        itemsIndexed(dataList) { index, item ->
                            MainContactItem(
                                contactViewModel,
                                item
                            )
                        }
                    }
                }

                is ContactUiState.EmptyData -> {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        textAlign = TextAlign.Center,
                        text = "Data Empty"
                    )
                }

                ContactUiState.Loading -> TODO()
            }


//        if (listDummyContact.isNullOrEmpty()) {
//
//        } else {
//            listDummyContact?.let { itemsDummy ->
//
//            }
//        }

        }
    }


}

@Composable
fun MainContactItem(
    contactViewModel: ContactViewModel,
    contactModel: ContactModel
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.DarkGray)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                contactModel.nameContact.toString(),
                color = Color.White,
                fontSize = 14.sp
            )
            Text(
                contactModel.phoneContact.toString(),
                color = Color.White,
                fontSize = 17.sp
            )
        }

        IconButton(
            onClick = {
                showDialog = true
            }
        ) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }

    if (showDialog) {
        DeleteConfirmationDialog(
            contactModel,
            onConfirm = {
                contactViewModel.deleteContactItem(contactModel)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

}

@Composable
fun DeleteConfirmationDialog(
    contact: ContactModel,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Contact") },
        text = { Text("Are you sure you want to delete ${contact.nameContact}?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}
