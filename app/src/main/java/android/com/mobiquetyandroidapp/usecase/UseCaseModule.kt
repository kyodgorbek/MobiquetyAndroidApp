package android.com.mobiquetyandroidapp.usecase

import org.koin.dsl.module.module

val useCaseModule = module {
    factory<GetProductsUseCase> { GetProductsUseCaseImpl(get()) }
}