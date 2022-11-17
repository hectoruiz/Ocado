package hector.ruiz.network.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponseApiModel(
    val clusters: List<ClusterApiModel?>? = null
)