package hector.ruiz.network.mappers

import hector.ruiz.commons.SingleMapper
import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.network.entities.ClusterApiModel
import javax.inject.Inject

class ClusterMapper @Inject constructor(private val productMapper: ProductMapper) :
    SingleMapper<ClusterApiModel, ClusterModel> {

    override fun apiModelToModel(apiModel: ClusterApiModel?): ClusterModel {
        return ClusterModel(
            tag = apiModel?.tag ?: "",
            items = apiModel?.items?.map { productMapper.apiModelToModel(it) } ?: emptyList()
        )
    }
}