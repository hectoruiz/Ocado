package hector.ruiz.network.datasource

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.entities.ProductResponseModel
import hector.ruiz.network.api.ApiService
import hector.ruiz.network.mappers.ProductMapper
import hector.ruiz.network.mappers.ProductResponseMapper
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val productResponseMapper: ProductResponseMapper,
    private val productMapper: ProductMapper
) :
    NetworkDataSource {

    override suspend fun getProducts(): ResponseResult<ProductResponseModel> {
        return apiService.getProducts().let {
            if (it.isSuccessful) {
                ResponseResult(data = productResponseMapper.apiModelToModel(it.body()),
                    errorCode = null)
            } else {
                ResponseResult(data = null, errorCode = it.code())
            }
        }
    }

    override suspend fun getProduct(productId: Int): ResponseResult<ProductModel> {
        return apiService.getProduct(productId).let {
            if (it.isSuccessful) {
                ResponseResult(data = productMapper.apiModelToModel(it.body()), errorCode = null)
            } else {
                ResponseResult(data = null, errorCode = it.code())
            }
        }
    }
}