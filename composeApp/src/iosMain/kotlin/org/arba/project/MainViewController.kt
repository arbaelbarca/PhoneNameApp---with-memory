package org.arba.project

import androidx.compose.ui.window.ComposeUIViewController
import org.arba.project.di.appModule
import org.arba.project.di.initializeKoin
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
//    initializeKoin()
    App()
}