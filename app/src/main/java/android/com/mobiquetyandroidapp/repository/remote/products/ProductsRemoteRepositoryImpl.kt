package android.com.mobiquetyandroidapp.repository.remote.products

import android.com.mobiquetyandroidapp.repository.remote.api.MainApi

class ProductsRemoteRepositoryImpl(private val mainApi: MainApi) : ProductsRemoteRepository {

    override suspend fun getProducts() = mainApi.productListAsync().await()
}