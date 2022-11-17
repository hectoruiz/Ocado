package hector.ruiz.network.mappers

import hector.ruiz.network.entities.ProductApiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTest {

    private val productMapper by lazy {
        ProductMapper()
    }

    @Test
    fun `apiModel null to model`() {
        val apiModel = null
        val model = productMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals(0, this.id)
            assertEquals("", this.price)
            assertEquals("", this.title)
            assertEquals("", this.imageUrl)
            assertEquals("", this.description)
            assertEquals("", this.allergyInformation)
        }
    }

    @Test
    fun `apiModel with null values to model`() {
        val apiModel = ProductApiModel(null, null, null, null,
            null, null)
        val model = productMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals(0, this.id)
            assertEquals("", this.price)
            assertEquals("", this.title)
            assertEquals("", this.imageUrl)
            assertEquals("", this.description)
            assertEquals("", this.allergyInformation)
        }
    }

    @Test
    fun `apiModel to model`() {
        val apiModel = ProductApiModel(12, "price", "title", "url",
            "desc", "allergy")
        val model = productMapper.apiModelToModel(apiModel)

        with(model) {
            assertEquals(12, this.id)
            assertEquals("price", this.price)
            assertEquals("title", this.title)
            assertEquals("url", this.imageUrl)
            assertEquals("desc", this.description)
            assertEquals("allergy", this.allergyInformation)
        }
    }
}