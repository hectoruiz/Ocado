package hector.ruiz.network.api

import hector.ruiz.network.entities.ProductApiModel
import hector.ruiz.network.entities.ProductResponseApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    suspend fun getProducts(): Response<ProductResponseApiModel>

    @GET("product/{$PRODUCT_ID}")
    suspend fun getProduct(@Path(PRODUCT_ID) productId: Int): Response<ProductApiModel>

    private companion object {
        const val PRODUCT_ID = "product_id"
    }
}