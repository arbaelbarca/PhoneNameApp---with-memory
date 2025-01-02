package org.arba.project.di

import org.arba.project.ContactViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    viewModel { ContactViewModel() }
}


fun initializeKoin(){
    startKoin {
        modules(appModule)
    }
}