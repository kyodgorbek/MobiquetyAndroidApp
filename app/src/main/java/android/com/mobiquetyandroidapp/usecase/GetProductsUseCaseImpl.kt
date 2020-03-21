package android.com.mobiquetyandroidapp.usecase

import android.com.mobiquetyandroidapp.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductsUseCaseImpl(private val dataRepository: DataRepository) : GetProductsUseCase {

    override suspend fun getProducts() = withContext(Dispatchers.IO) {
        dataRepository.getProducts()
    }
}