package android.com.mobiquetyandroidapp.usecase.getproducts

import android.com.mobiquetyandroidapp.data.remote.Product
import android.com.mobiquetyandroidapp.data.remote.SalePrice
import android.com.mobiquetyandroidapp.repository.DataRepository
import android.com.mobiquetyandroidapp.usecase.GetProductsUseCase
import android.com.mobiquetyandroidapp.usecase.GetProductsUseCaseImpl
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetProductsUseCaseImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockDataRepository: DataRepository = mock()
    private lateinit var getProductsUseCase: GetProductsUseCase

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("IO thread")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        getProductsUseCase = GetProductsUseCaseImpl(mockDataRepository)

    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getProducts test`() = runBlocking {
        val productList = ArrayList<Product>().apply {
            val salePrice = SalePrice("er", "e")
            val product = Product("1", "1", "name", "url", salePrice)
            add(product)
        }

        whenever(mockDataRepository.getProducts()).thenReturn(productList)
        val products = getProductsUseCase.getProducts()
        Assert.assertEquals(products, productList)
    }
}