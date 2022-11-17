package hector.ruiz.network.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClusterApiModel(
    val tag: String? = null,
    val items: List<ProductApiModel?>? = null
)