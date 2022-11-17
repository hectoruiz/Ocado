package hector.ruiz.data

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.data.repositories.ProductRepositoryImpl
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.entities.ProductResponseModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ProductApiModelRepositoryImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var networkDataSource: NetworkDataSource

    private val userRepositoryImpl by lazy {
        ProductRepositoryImpl(networkDataSource)
    }

    @Test
    fun `product repository requesting getProducts unsuccessfully`() {
        coEvery { networkDataSource.getProducts() } returns ResponseResult(
            errorCode = ERROR_CODE,
            data = null
        )
        val result = runBlocking {
            userRepositoryImpl.getProducts()
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `product repository requesting getProducts successfully`() {
        val productResponseModel = mockk<ProductResponseModel>()

        coEvery { networkDataSource.getProducts() } returns ResponseResult(
            errorCode = null,
            data = productResponseModel
        )
        val result = runBlocking {
            userRepositoryImpl.getProducts()
        }

        assertNull(result.errorCode)
        assertEquals(productResponseModel, result.data)
    }

    @Test
    fun `product repository requesting getProduct unsuccessfully`() {
        coEvery { networkDataSource.getProduct(PRODUCT_ID) } returns ResponseResult(
            errorCode = ERROR_CODE,
            data = null
        )
        val result = runBlocking {
            userRepositoryImpl.getProduct(PRODUCT_ID)
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `product repository requesting getProduct successfully`() {
        val productApiModelResponse = mockk<ProductModel>()

        coEvery { networkDataSource.getProduct(PRODUCT_ID) } returns ResponseResult(
            errorCode = null,
            data = productApiModelResponse
        )
        val result = runBlocking {
            userRepositoryImpl.getProduct(PRODUCT_ID)
        }

        assertNull(result.errorCode)
        assertEquals(productApiModelResponse, result.data)
    }

    private companion object {
        const val PRODUCT_ID = 12
        const val ERROR_CODE = 400
    }
}