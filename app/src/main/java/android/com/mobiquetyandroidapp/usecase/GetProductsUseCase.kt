package android.com.mobiquetyandroidapp.usecase

import android.com.mobiquetyandroidapp.data.remote.Product

interface GetProductsUseCase {

    suspend fun getProducts(): List<Product>
}