package android.com.mobiquetyandroidapp.screen.main

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
}