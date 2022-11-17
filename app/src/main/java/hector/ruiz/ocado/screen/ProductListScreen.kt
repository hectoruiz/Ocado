package hector.ruiz.ocado.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import hector.ruiz.ocado.R
import hector.ruiz.ocado.ui.composables.CircularProgress
import hector.ruiz.ocado.ui.composables.ErrorData
import hector.ruiz.ocado.ui.composables.ProductList
import hector.ruiz.ocado.ui.theme.OcadoTheme
import hector.ruiz.ocado.viewmodels.ProductListViewModel

@Preview
@Composable
fun ProductListScreen(navigateTo: (Int) -> Unit) {

    val productListViewModel = hiltViewModel<ProductListViewModel>()

    OcadoTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(id = R.string.list_title)) })
            },
            content = { paddingValues ->
                Box(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(id = R.dimen.small_padding_parent))) {
                    val clusterList = productListViewModel.uiState.value.clusterList

                    Column {
                        ProductList(clusterList) { param ->
                            navigateTo(param)
                        }
                    }

                    if (productListViewModel.uiState.value.loading) {
                        CircularProgress(modifier = Modifier.align(Alignment.Center))
                    }

                    if (productListViewModel.uiState.value.showError) {
                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()) {
                            ErrorData {
                                productListViewModel.getProducts()
                            }
                        }
                    }
                }
            }
        )
    }
}