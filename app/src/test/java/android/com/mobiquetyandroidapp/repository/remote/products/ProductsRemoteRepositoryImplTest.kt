package android.com.mobiquetyandroidapp.repository.remote.products

import android.com.mobiquetyandroidapp.data.remote.Category
import android.com.mobiquetyandroidapp.data.remote.Product
import android.com.mobiquetyandroidapp.data.remote.SalePrice
import android.com.mobiquetyandroidapp.repository.remote.api.MainApi
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
class ProductsRemoteRepositoryImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockMainApi: MainApi = mock()
    private lateinit var productsRemoteRepository: ProductsRemoteRepository

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("IO thread")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        productsRemoteRepository = ProductsRemoteRepositoryImpl(mockMainApi)

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
            add(Product("1", "1", "name", "url", salePrice))
        }

        val categories = ArrayList<Category>().apply {
            add(Category("id", "name", productList))
        }
        whenever(mockMainApi.productListAsync()).thenReturn(GlobalScope.async { categories })
        val products = productsRemoteRepository.getProducts()
        Assert.assertEquals(products, categories)
    }
}