package android.com.mobiquetyandroidapp.screen.details

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val detailsModule = module {
    viewModel { DetailsViewModel() }
}