package android.com.mobiquetyandroidapp.repository

import android.com.mobiquetyandroidapp.data.remote.Product
import android.com.mobiquetyandroidapp.repository.remote.products.ProductsRemoteRepository

class DataRepositoryImpl(private val remoteRepository: ProductsRemoteRepository) : DataRepository {

    override suspend fun getProducts(): List<Product> {
        val productList = ArrayList<Product>()
        remoteRepository.getProducts().forEach { category ->
            productList.addAll(category.products)
        }

        return productList.sortedWith(compareBy({ it.categoryId }, { it.name }))
    }
}