package hector.ruiz.domain.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.entities.ProductResponseModel

interface ProductRepository {

    suspend fun getProducts(): ResponseResult<ProductResponseModel>

    suspend fun getProduct(productId: Int): ResponseResult<ProductModel>
}