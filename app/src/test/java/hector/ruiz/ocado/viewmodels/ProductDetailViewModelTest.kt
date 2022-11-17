package hector.ruiz.ocado.viewmodels

import androidx.lifecycle.SavedStateHandle
import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.usecases.GetProductUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class ProductDetailViewModelTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var getProductUseCase: GetProductUseCase

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    private val productDetailViewModel by lazy {
        ProductDetailViewModel(savedStateHandle, getProductUseCase)
    }

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        every { savedStateHandle.get("productId") as Int? } returns PRODUCT_ID
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get product answer successfully`() {
        val productApiModelResponse = mockk<ProductModel>(relaxed = true)
        coEvery { getProductUseCase(PRODUCT_ID) } returns ResponseResult(
            errorCode = null,
            data = productApiModelResponse
        )
        initState()
        runTest { productDetailViewModel.getProduct(PRODUCT_ID) }

        with(productDetailViewModel.uiState.value) {
            assertFalse(this.loading)
            assertFalse(this.showError)
            assertNotNull(this.productModel)
            assertEquals(productApiModelResponse, this.productModel)
        }
    }

    @Test
    fun `get product answer successfully with null product`() {
        coEvery { getProductUseCase(PRODUCT_ID) } returns ResponseResult(
            errorCode = null,
            data = null
        )
        initState()
        runTest { productDetailViewModel.getProduct(PRODUCT_ID) }
        errorState()
    }

    @Test
    fun `get product answer unsuccessfully`() {
        coEvery { getProductUseCase(PRODUCT_ID) } returns ResponseResult(
            errorCode = ERROR_CODE,
            data = null
        )
        initState()
        runTest { productDetailViewModel.getProduct(PRODUCT_ID) }
        errorState()
    }

    @Test
    fun `get product throws exception`() {
        coEvery { getProductUseCase(PRODUCT_ID) } throws TimeoutException()
        initState()
        runTest { productDetailViewModel.getProduct(PRODUCT_ID) }
        errorState()
    }

    private fun initState() {
        with(productDetailViewModel.uiState.value) {
            assertTrue(this.loading)
            assertFalse(this.showError)
            assertNull(this.productModel)
        }
    }

    private fun errorState() {
        with(productDetailViewModel.uiState.value) {
            assertFalse(this.loading)
            assertTrue(this.showError)
            assertNull(this.productModel)
        }
    }

    private companion object {
        const val PRODUCT_ID: Int = 8
        const val ERROR_CODE = 400
    }
}