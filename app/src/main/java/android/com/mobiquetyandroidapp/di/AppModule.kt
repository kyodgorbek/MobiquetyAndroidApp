package android.com.mobiquetyandroidapp.di

import android.com.mobiquetyandroidapp.screen.details.detailsModule
import android.com.mobiquetyandroidapp.screen.main.mainModule
import android.com.mobiquetyandroidapp.usecase.useCaseModule

val appModule = listOf(
        retrofitModule,
        repositoryModule,
        useCaseModule,
        mainModule,
        detailsModule
)