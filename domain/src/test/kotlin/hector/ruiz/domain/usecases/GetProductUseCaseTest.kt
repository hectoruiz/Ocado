package hector.ruiz.domain.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ProductModel
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

class GetProductUseCaseTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var productRepository: ProductRepository

    @MockK
    private lateinit var dispatcher: Dispatchers

    private val getProductUseCase by lazy {
        GetProductUseCase(productRepository, dispatcher.IO)
    }

    @Test
    fun `getProductUseCase request successfully getProduct`() {
        val responseData = mockk<ResponseResult<ProductModel>>()
        val data = mockk<ProductModel>()

        coEvery { productRepository.getProduct(PRODUCT_ID) } returns responseData
        every { responseData.data } returns data
        every { responseData.errorCode } returns null

        val result = runBlocking { getProductUseCase(PRODUCT_ID) }

        assertNull(result.errorCode)
        assertEquals(data, result.data)
    }

    @Test
    fun `getProductUseCase request successfully getProduct with null product`() {
        val responseData = mockk<ResponseResult<ProductModel>>()

        coEvery { productRepository.getProduct(PRODUCT_ID) } returns responseData
        every { responseData.data } returns null
        every { responseData.errorCode } returns null

        val result = runBlocking { getProductUseCase(PRODUCT_ID) }

        assertNull(result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `getProductUseCase request unsuccessfully getProduct`() {
        val responseData = mockk<ResponseResult<ProductModel>>()

        coEvery { productRepository.getProduct(PRODUCT_ID) } returns responseData
        every { responseData.data } returns null
        every { responseData.errorCode } returns ERROR_CODE

        val result = runBlocking { getProductUseCase(PRODUCT_ID) }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    private companion object {
        const val PRODUCT_ID = 18
        const val ERROR_CODE = 400
    }
}