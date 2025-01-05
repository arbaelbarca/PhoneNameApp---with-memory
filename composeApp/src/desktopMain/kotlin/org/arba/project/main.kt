package org.arba.project

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.arba.project.di.initializeKoin

fun main() = application {

    initializeKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "TestKMP",
        state = WindowState(
            position = WindowPosition(Alignment.Center)
        )
    ) {
        window.minimumSize = java.awt.Dimension(1280,768)
        App()
    }
}