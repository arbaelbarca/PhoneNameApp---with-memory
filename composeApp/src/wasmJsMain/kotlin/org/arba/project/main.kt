package org.arba.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.lifecycle.ViewModelProvider
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
//        val contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
//        App()
    }
}