package hector.ruiz.ocado.viewmodels

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.domain.usecases.GetProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
class ProductListViewModelTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var getProductsUseCase: GetProductsUseCase

    private val productListViewModel by lazy {
        ProductListViewModel(getProductsUseCase)
    }

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get products answer successfully`() {
        val productResponse = mockk<List<ClusterModel>>(relaxed = true)
        coEvery { getProductsUseCase() } returns ResponseResult(
            errorCode = null,
            data = productResponse
        )
        initState()
        runTest { productListViewModel.getProducts() }

        with(productListViewModel.uiState.value) {
            assertFalse(this.loading)
            assertFalse(this.showError)
            assertFalse(this.clusterList.isEmpty())
            assertEquals(productResponse, this.clusterList)
        }
    }

    @Test
    fun `get products answer successfully with empty list`() {
        coEvery { getProductsUseCase() } returns ResponseResult(
            errorCode = null,
            data = emptyList()
        )
        initState()
        runTest { productListViewModel.getProducts() }
        errorState()
    }

    @Test
    fun `get products answer unsuccessfully`() {
        coEvery { getProductsUseCase() } returns ResponseResult(
            errorCode = ERROR_CODE,
            data = null
        )
        initState()
        runTest { productListViewModel.getProducts() }
        errorState()
    }

    @Test
    fun `get products throws exception`() {
        coEvery { getProductsUseCase() } throws TimeoutException()
        initState()
        runTest { productListViewModel.getProducts() }
        errorState()
    }

    private fun initState() {
        with(productListViewModel.uiState.value) {
            assertTrue(this.loading)
            assertFalse(this.showError)
            assertTrue(this.clusterList.isEmpty())
        }
    }

    private fun errorState() {
        with(productListViewModel.uiState.value) {
            assertFalse(this.loading)
            assertTrue(this.showError)
            assertTrue(this.clusterList.isEmpty())
        }
    }

    private companion object {
        const val ERROR_CODE = 400
    }
}