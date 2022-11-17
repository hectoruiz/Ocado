package hector.ruiz.ocado.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.ocado.R

const val TAG_PRODUCT_LIST = "productsList"

@Preview
@Composable
fun ProductList(clusterList: List<ClusterModel>, onItemClicked: (Int) -> Unit) {

    LazyColumn(modifier = Modifier.testTag(TAG_PRODUCT_LIST),
        content = {
            items(clusterList.size) { index ->
                clusterList[index].tag.let { header ->
                    val paddingTop =
                        if (index != 0) dimensionResource(id = R.dimen.big_padding_parent) else 0.dp
                    Text(
                        style = MaterialTheme.typography.h1,
                        text = header,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = paddingTop),
                        textAlign = TextAlign.Center
                    )
                }
                clusterList[index].items.forEach { product ->
                    Box(modifier = Modifier.clickable { onItemClicked(product.id) }) {
                        Card(elevation = dimensionResource(id = R.dimen.picture_elevation),
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.big_padding_parent),
                                    dimensionResource(id = R.dimen.small_padding_parent),
                                    dimensionResource(id = R.dimen.big_padding_parent),
                                    dimensionResource(id = R.dimen.small_padding_parent))
                                .fillMaxSize()) {
                            Row(modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.small_padding_parent)),
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                ProductInfo(product,
                                    Modifier.size(dimensionResource(id = R.dimen.picture_list_size)))
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ProductInfo(product: ProductModel, modifier: Modifier) {

    val painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
        .data(data = product.imageUrl).apply(block = fun ImageRequest.Builder.() {
            placeholder(R.drawable.photo_placeholder)
            error(R.drawable.photo_error)
        }).build())
    val pictureProperties =
        modifier.then(Modifier.border(dimensionResource(id = R.dimen.picture_border),
            MaterialTheme.colors.primary))

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.picture_description),
        modifier = pictureProperties
    )
    Column(modifier = Modifier
        .padding(dimensionResource(id = R.dimen.small_padding_parent))
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            style = MaterialTheme.typography.h1,
            text = product.title,
            textAlign = TextAlign.Center
        )
        Text(
            style = MaterialTheme.typography.h2,
            text = "${product.price} €"
        )
    }
}

@Preview
@Composable
fun ProductDetail(product: ProductModel) {

    Text(
        style = MaterialTheme.typography.h2,
        text = product.description,
        modifier = Modifier.padding(
            vertical = dimensionResource(id = R.dimen.small_padding_parent)
        ),
        textAlign = TextAlign.Center
    )
    Row(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.small_padding_parent)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = R.drawable.ic_allergy),
            contentDescription = stringResource(id = R.string.allergy_description))
        Text(
            style = MaterialTheme.typography.h2,
            text = product.allergyInformation,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding_parent)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}