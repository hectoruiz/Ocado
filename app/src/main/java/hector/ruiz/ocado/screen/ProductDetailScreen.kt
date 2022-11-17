package hector.ruiz.ocado.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import hector.ruiz.ocado.R
import hector.ruiz.ocado.ui.composables.CircularProgress
import hector.ruiz.ocado.ui.composables.ErrorData
import hector.ruiz.ocado.ui.composables.ProductDetail
import hector.ruiz.ocado.ui.composables.ProductInfo
import hector.ruiz.ocado.ui.theme.OcadoTheme
import hector.ruiz.ocado.viewmodels.ProductDetailViewModel

@Composable
fun ProductDetailScreen(productId: Int) {

    val productDetailViewModel = hiltViewModel<ProductDetailViewModel>()
    val idProduct by rememberSaveable { mutableStateOf(productId) }

    OcadoTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(id = R.string.detail_title)) })
            },
            content = { paddingValues ->
                Box(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(id = R.dimen.small_padding_parent))) {
                    val product = productDetailViewModel.uiState.value.productModel

                    if (product != null) {
                        Column(modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(dimensionResource(id = R.dimen.small_padding_parent)),
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            ProductInfo(
                                product = product,
                                modifier = Modifier
                                    .padding(bottom = dimensionResource(id = R.dimen.big_padding_parent))
                                    .size(dimensionResource(id = R.dimen.picture_detail_size)))
                            ProductDetail(product)
                        }
                    }

                    if (productDetailViewModel.uiState.value.loading) {
                        CircularProgress(modifier = Modifier.align(Alignment.Center))
                    }

                    if (productDetailViewModel.uiState.value.showError) {
                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()) {
                            ErrorData {
                                productDetailViewModel.getProduct(idProduct)
                            }
                        }
                    }
                }
            }
        )
    }
}