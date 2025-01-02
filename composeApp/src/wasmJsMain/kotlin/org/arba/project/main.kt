package org.arba.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.lifecycle.ViewModelProvider
import kotlinx.browser.document
import org.arba.project.di.appModule
import org.arba.project.di.initializeKoin
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initializeKoin()

    ComposeViewport(document.body!!) {
//        val contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        App()
    }
}