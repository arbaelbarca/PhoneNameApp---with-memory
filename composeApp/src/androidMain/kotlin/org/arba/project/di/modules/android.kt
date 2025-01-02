package org.arba.project.di.modules

import org.arba.project.AndroidPlatform
import org.arba.project.ContactModel
import org.arba.project.ContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module



//val appModules = module {
//    viewModel { ContactViewModel() }
//}
//
//fun initializeKoin(){
//    startKoin {
//        modules(appModules)
//    }
//}