package hector.ruiz.network.mappers

import hector.ruiz.commons.SingleMapper
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.network.entities.ProductApiModel

class ProductMapper : SingleMapper<ProductApiModel, ProductModel> {

    override fun apiModelToModel(apiModel: ProductApiModel?): ProductModel {
        return ProductModel(
            id = apiModel?.id ?: 0,
            price = apiModel?.price ?: "",
            title = apiModel?.title ?: "",
            imageUrl = apiModel?.imageUrl ?: "",
            description = apiModel?.description ?: "",
            allergyInformation = apiModel?.allergyInformation ?: ""
        )
    }
}