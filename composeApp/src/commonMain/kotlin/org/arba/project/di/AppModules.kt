package org.arba.project.di

import org.arba.project.ContactViewModel
import org.arba.project.viewmodel.ArticlesViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    viewModel { ContactViewModel() }
    viewModel { ArticlesViewModel() }
}


fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}