package hector.ruiz.network.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductApiModel(
    val id: Int? = null,
    val price: String? = null,
    val title: String? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val allergyInformation: String? = null
)