package org.arba.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.ViewModelProvider
import org.arba.project.di.appModule
import org.arba.project.di.initializeKoin
import org.koin.core.context.startKoin

fun main() = application {

    initializeKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "TestKMP",
    ) {
        App()
    }
}