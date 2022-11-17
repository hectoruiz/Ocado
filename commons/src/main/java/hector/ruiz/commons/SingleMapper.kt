package hector.ruiz.commons

interface SingleMapper<ApiModel, Model> {

    fun apiModelToModel(apiModel: ApiModel?): Model
}