package hector.ruiz.network.mappers

import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.network.entities.ClusterApiModel
import hector.ruiz.network.entities.ProductApiModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ClusterMapperTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var productMapper: ProductMapper

    private val clusterMapper by lazy {
        ClusterMapper(productMapper)
    }

    @Test
    fun `apiModel null to model`() {
        val apiModel = null
        val model = clusterMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals("", this.tag)
            assertEquals(emptyList<ProductModel>(), this.items)
            assertEquals(0, this.items.size)
        }
    }

    @Test
    fun `apiModel with null values to model`() {
        val apiModel = ClusterApiModel(null, null)
        val model = clusterMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals("", this.tag)
            assertEquals(emptyList<ProductModel>(), this.items)
            assertEquals(0, this.items.size)
        }
    }

    @Test
    fun `apiModel to model`() {
        val productApiModel = mockk<ProductApiModel>()
        val productModel = mockk<ProductModel>()
        every { productMapper.apiModelToModel(productApiModel) } returns productModel

        val apiModel = ClusterApiModel("tag", listOf(productApiModel, productApiModel))
        val model = clusterMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals("tag", this.tag)
            assertEquals(listOf(productModel, productModel), this.items)
            assertEquals(2, this.items.size)
        }
    }
}