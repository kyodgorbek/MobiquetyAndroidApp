package android.com.mobiquetyandroidapp.di

import android.com.mobiquetyandroidapp.repository.DataRepository
import android.com.mobiquetyandroidapp.repository.DataRepositoryImpl
import android.com.mobiquetyandroidapp.repository.remote.products.ProductsRemoteRepository
import android.com.mobiquetyandroidapp.repository.remote.products.ProductsRemoteRepositoryImpl
import org.koin.dsl.module.module

val repositoryModule = module {
    single<ProductsRemoteRepository> { ProductsRemoteRepositoryImpl(get()) }
    single<DataRepository> { DataRepositoryImpl(get()) }
}