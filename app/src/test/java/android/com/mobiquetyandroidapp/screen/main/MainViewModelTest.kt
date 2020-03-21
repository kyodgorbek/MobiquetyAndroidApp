package android.com.mobiquetyandroidapp.screen.main

import android.com.mobiquetyandroidapp.data.remote.Product
import android.com.mobiquetyandroidapp.data.remote.SalePrice
import android.com.mobiquetyandroidapp.usecase.GetProductsUseCase
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockIsLoadingObserver: Observer<Boolean> = mock()
    private val mockIsProductsLoadedObserver: Observer<List<Product>> = mock()

    private val mockGetProductsUseCase = mock<GetProductsUseCase>()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var viewModel: MainViewModel

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = MainViewModel((mockGetProductsUseCase))

    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `Show spinner test`() = runBlockingTest {
        viewModel.isLoading.observeForever(mockIsLoadingObserver)
        viewModel.getProducts()

        Mockito.verify(mockIsLoadingObserver, Mockito.atLeastOnce()).onChanged(true)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `Hide spinner test`() = runBlockingTest {
        viewModel.isLoading.observeForever(mockIsLoadingObserver)
        viewModel.getProducts()

        Mockito.verify(mockIsLoadingObserver, Mockito.atLeastOnce()).onChanged(false)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `Get products test`() = runBlockingTest {
        val productList = ArrayList<Product>()
        val salePrice = SalePrice("er", "e")
        val product = Product("1", "1", "name", "url", salePrice)
        productList.add(product)
        whenever(mockGetProductsUseCase.getProducts()).thenReturn(productList)
        viewModel.isProductsLoaded.observeForever(mockIsProductsLoadedObserver)
        viewModel.getProducts()

        Mockito.verify(mockIsProductsLoadedObserver).onChanged(productList)
    }
}