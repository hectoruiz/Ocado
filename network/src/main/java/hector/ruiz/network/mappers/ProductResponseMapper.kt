package hector.ruiz.network.mappers

import hector.ruiz.commons.SingleMapper
import hector.ruiz.domain.entities.ProductResponseModel
import hector.ruiz.network.entities.ProductResponseApiModel
import javax.inject.Inject

class ProductResponseMapper @Inject constructor(private val clusterMapper: ClusterMapper) :
    SingleMapper<ProductResponseApiModel, ProductResponseModel> {

    override fun apiModelToModel(apiModel: ProductResponseApiModel?): ProductResponseModel {
        return ProductResponseModel(
            clusters = apiModel?.clusters?.map { clusterMapper.apiModelToModel(it) } ?: emptyList()
        )
    }
}