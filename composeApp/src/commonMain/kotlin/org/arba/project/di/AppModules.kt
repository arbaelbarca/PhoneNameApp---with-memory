package org.arba.project.di

import org.arba.project.ContactViewModel
import org.arba.project.data.network.ClientHttp
import org.arba.project.data.repository.ArticlesRepository
import org.arba.project.viewmodel.ArticlesViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val provideViewModel = module {
    viewModel { ContactViewModel() }
//    viewModelOf(::ArticlesViewModel).bind(::ArticlesRepository())
    viewModel { ArticlesViewModel(get()) }
}

val provideRepostiry = module {
    singleOf(::ArticlesRepository)
//    single { ArticlesRepository }
}

val provideHttpClient = module {
    singleOf(::ClientHttp)
}

fun initializeKoin() {
    startKoin {
        modules(
            provideViewModel,
            provideRepostiry,
            provideHttpClient
        )
    }
}