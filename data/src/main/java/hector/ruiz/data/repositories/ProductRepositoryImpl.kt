package hector.ruiz.data.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.entities.ProductResponseModel
import hector.ruiz.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    ProductRepository {

    override suspend fun getProducts(): ResponseResult<ProductResponseModel> =
        networkDataSource.getProducts()

    override suspend fun getProduct(productId: Int): ResponseResult<ProductModel> =
        networkDataSource.getProduct(productId)
}