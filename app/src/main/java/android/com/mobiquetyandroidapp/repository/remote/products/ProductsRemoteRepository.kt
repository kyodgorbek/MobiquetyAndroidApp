package android.com.mobiquetyandroidapp.repository.remote.products

import android.com.mobiquetyandroidapp.data.remote.Category

interface ProductsRemoteRepository {

    suspend fun getProducts(): List<Category>
}