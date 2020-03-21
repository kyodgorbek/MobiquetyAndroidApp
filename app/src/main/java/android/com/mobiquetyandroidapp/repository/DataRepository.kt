package android.com.mobiquetyandroidapp.repository

import android.com.mobiquetyandroidapp.data.remote.Product

interface DataRepository {

    suspend fun getProducts(): List<Product>
}