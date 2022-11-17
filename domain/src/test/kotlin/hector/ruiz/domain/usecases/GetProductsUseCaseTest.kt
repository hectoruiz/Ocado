package hector.ruiz.domain.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.domain.entities.ProductResponseModel
import hector.ruiz.domain.repositories.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GetProductsUseCaseTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var productRepository: ProductRepository

    @MockK
    private lateinit var dispatcher: Dispatchers

    private val getProductsUseCase by lazy {
        GetProductsUseCase(productRepository, dispatcher.IO)
    }

    @Test
    fun `getProductsUseCase request successfully getProducts`() {
        val responseData = mockk<ResponseResult<ProductResponseModel>>()
        val data = listOf<ClusterModel>(mockk(), mockk(), mockk(), mockk(), mockk(), mockk())

        coEvery { productRepository.getProducts() } returns responseData
        every { responseData.data?.clusters } returns data
        every { responseData.errorCode } returns null

        val result = runBlocking { getProductsUseCase() }

        assertNull(result.errorCode)
        assertEquals(data, result.data)
        assertEquals(6, result.data?.size)
    }

    @Test
    fun `getProductsUseCase request successfully getProducts with null product list`() {
        val responseData = mockk<ResponseResult<ProductResponseModel>>()

        coEvery { productRepository.getProducts() } returns responseData
        every { responseData.data?.clusters } returns emptyList()
        every { responseData.errorCode } returns null

        val result = runBlocking { getProductsUseCase() }

        assertNull(result.errorCode)
        assertEquals(0, result.data?.size)
    }

    @Test
    fun `getProductsUseCase request unsuccessfully getProducts`() {
        val responseData = mockk<ResponseResult<ProductResponseModel>>()
        every { responseData.data } returns null
        every { responseData.errorCode } returns ERROR_CODE

        coEvery { productRepository.getProducts() } returns responseData

        val result = runBlocking { getProductsUseCase() }

        assertEquals(ERROR_CODE, result.errorCode)
        assertEquals(0, result.data?.size)
    }

    private companion object {
        const val ERROR_CODE = 400
    }
}