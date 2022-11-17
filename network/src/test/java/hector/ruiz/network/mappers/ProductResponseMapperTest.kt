package hector.ruiz.network.mappers

import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.network.entities.ClusterApiModel
import hector.ruiz.network.entities.ProductResponseApiModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductResponseMapperTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var clusterMapper: ClusterMapper

    private val productResponseMapper by lazy {
        ProductResponseMapper(clusterMapper)
    }

    @Test
    fun `apiModel null to model`() {
        val apiModel = null
        val model = productResponseMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals(emptyList<ClusterModel>(), this.clusters)
            assertEquals(0, this.clusters.size)
        }
    }

    @Test
    fun `apiModel with null values to model`() {
        val apiModel = null
        val model = productResponseMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals(emptyList<ClusterModel>(), this.clusters)
            assertEquals(0, this.clusters.size)
        }
    }

    @Test
    fun `apiModel to model`() {
        val clusterApiModel = mockk<ClusterApiModel>()
        val clusterModel = mockk<ClusterModel>()
        every { clusterMapper.apiModelToModel(clusterApiModel) } returns clusterModel

        val apiModel =
            ProductResponseApiModel(listOf(clusterApiModel, clusterApiModel, clusterApiModel))
        val model = productResponseMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals(listOf(clusterModel, clusterModel, clusterModel), this.clusters)
            assertEquals(3, this.clusters.size)
        }
    }
}