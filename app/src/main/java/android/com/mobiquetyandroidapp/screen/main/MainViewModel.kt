package android.com.mobiquetyandroidapp.screen.main

import android.com.mobiquetyandroidapp.data.remote.Product
import android.com.mobiquetyandroidapp.screen.base.BaseViewModel
import android.com.mobiquetyandroidapp.usecase.GetProductsUseCase
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch

open class MainViewModel(private val readProductsUseCase: GetProductsUseCase) : BaseViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    init {
        getProducts()
    }

    val isLoading = MutableLiveData<Boolean>()
    val isProductsLoaded = MutableLiveData<List<Product>>()

    fun getProducts() {
        launch {
            try {
                isLoading.value = true
                isProductsLoaded.value = readProductsUseCase.getProducts()
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
            } finally {
                isLoading.value = false
            }
        }
    }
}