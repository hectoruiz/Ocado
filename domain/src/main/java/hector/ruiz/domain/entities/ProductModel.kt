package hector.ruiz.domain.entities

data class ProductModel(
    val id: Int,
    val price: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val allergyInformation: String
)