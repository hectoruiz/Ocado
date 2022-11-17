package hector.ruiz.ocado.ui.composables

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.ocado.ui.theme.OcadoTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ProductListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val product = ProductModel(id = 1,
        price = "10",
        title = "title",
        imageUrl = "imageUrl",
        description = "description",
        allergyInformation = "allergyInformation")
    private val cluster = ClusterModel("tag", listOf(product, product))
    private val clusters = listOf(cluster, cluster, cluster, cluster)

    private var idProductClicked = 0

    private fun initTest(clusterList: List<ClusterModel>) {
        composeTestRule.setContent {
            OcadoTheme {
                ProductList(clusterList) { param -> idProductClicked = param }
            }
        }
    }

    @Test
    fun testOnClickItem() {
        initTest(clusters)
        composeTestRule.onNodeWithTag(TAG_PRODUCT_LIST).onChildren()
            .filter(hasClickAction())[0].performClick()
        assertEquals(product.id, idProductClicked)
    }

    @Test
    fun testProductListDisplayWithItems() {
        initTest(clusters)
        with(composeTestRule.onNodeWithTag(TAG_PRODUCT_LIST).onChildren()
            .filter(hasClickAction())[0]) {
            this.assertContentDescriptionContains("Picture")
            this.assertTextContains(product.title)
            this.assertTextContains("${product.price} €")
        }
    }

    @Test
    fun testProductListDisplayWithNoProducts() {
        initTest(emptyList())
        composeTestRule.onNodeWithTag(TAG_PRODUCT_LIST).onChildren().assertCountEquals(0)
    }
}