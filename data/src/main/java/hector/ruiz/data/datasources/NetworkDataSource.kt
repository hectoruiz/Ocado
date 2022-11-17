package hector.ruiz.data.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.entities.ProductResponseModel

interface NetworkDataSource {

    suspend fun getProducts(): ResponseResult<ProductResponseModel>

    suspend fun getProduct(productId: Int): ResponseResult<ProductModel>
}