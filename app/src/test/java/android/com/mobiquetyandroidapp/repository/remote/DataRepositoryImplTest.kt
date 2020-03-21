package android.com.mobiquetyandroidapp.repository.remote

import android.com.mobiquetyandroidapp.data.remote.Category
import android.com.mobiquetyandroidapp.data.remote.Product
import android.com.mobiquetyandroidapp.data.remote.SalePrice
import android.com.mobiquetyandroidapp.repository.DataRepository
import android.com.mobiquetyandroidapp.repository.DataRepositoryImpl
import android.com.mobiquetyandroidapp.repository.remote.products.ProductsRemoteRepository
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
class DataRepositoryImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRemoteRepository: ProductsRemoteRepository = mock()
    private lateinit var dataRepository: DataRepository

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("IO thread")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        dataRepository = DataRepositoryImpl(mockRemoteRepository)

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
        whenever(mockRemoteRepository.getProducts()).thenReturn(categories)
        val products = dataRepository.getProducts()
        Assert.assertEquals(products, productList)
    }
}