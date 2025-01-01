package org.arba.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.ViewModelProvider

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TestKMP",
    ) {
        App()
    }
}