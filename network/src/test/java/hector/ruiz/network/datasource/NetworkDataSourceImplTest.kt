package hector.ruiz.network.datasource

import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.entities.ProductResponseModel
import hector.ruiz.network.api.ApiClient
import hector.ruiz.network.api.ApiService
import hector.ruiz.network.entities.ProductApiModel
import hector.ruiz.network.entities.ProductResponseApiModel
import hector.ruiz.network.mappers.ProductMapper
import hector.ruiz.network.mappers.ProductResponseMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class NetworkDataSourceImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var apiClient: ApiClient

    @MockK
    private lateinit var retrofit: Retrofit

    @MockK
    private lateinit var apiService: ApiService

    @MockK
    private lateinit var productResponseMapper: ProductResponseMapper

    @MockK
    private lateinit var productMapper: ProductMapper

    private val networkDataSourceImpl by lazy {
        NetworkDataSourceImpl(apiService, productResponseMapper, productMapper)
    }

    @Before
    fun setUp() {
        every { apiClient.retrofit } returns retrofit
        every { retrofit.create<ApiService>() } returns apiService
    }

    @Test
    fun `error requesting products`() {
        coEvery { apiService.getProducts() } returns Response.error(
            ERROR_CODE,
            mockk(relaxed = true)
        )
        val result = runBlocking {
            networkDataSourceImpl.getProducts()
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `success requesting products`() {
        val responseData = mockk<ProductResponseApiModel>()
        val model = mockk<ProductResponseModel>()

        coEvery { apiService.getProducts() } returns Response.success(
            SUCCESS_CODE,
            responseData
        )
        every { productResponseMapper.apiModelToModel(responseData) } returns model

        val result = runBlocking {
            networkDataSourceImpl.getProducts()
        }

        assertNull(result.errorCode)
        assertEquals(model, result.data)
    }

    @Test
    fun `error requesting specific product`() {
        coEvery { apiService.getProduct(PRODUCT_ID) } returns Response.error(
            ERROR_CODE,
            mockk(relaxed = true)
        )
        val result = runBlocking {
            networkDataSourceImpl.getProduct(PRODUCT_ID)
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `success requesting specific product`() {
        val responseData = mockk<ProductApiModel>()
        val model = mockk<ProductModel>()

        coEvery { apiService.getProduct(PRODUCT_ID) } returns Response.success(
            SUCCESS_CODE,
            responseData
        )
        every { productMapper.apiModelToModel(responseData) } returns model

        val result = runBlocking {
            networkDataSourceImpl.getProduct(PRODUCT_ID)
        }

        assertNull(result.errorCode)
        assertEquals(model, result.data)
    }

    private companion object {
        const val PRODUCT_ID = 12
        const val ERROR_CODE = 400
        const val SUCCESS_CODE = 200
    }
}