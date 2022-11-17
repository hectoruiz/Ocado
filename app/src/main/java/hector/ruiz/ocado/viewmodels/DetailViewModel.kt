package hector.ruiz.ocado.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.usecases.GetProductUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val productModel: ProductModel? = null,
    val loading: Boolean = true,
    val showError: Boolean = false,
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductUseCase: GetProductUseCase,
) : ViewModel() {

    private val productId: Int? = savedStateHandle["productId"]

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        showError()
    }

    private val _uiState = mutableStateOf(DetailUiState())
    val uiState: State<DetailUiState> = _uiState

    init {
        getProduct(productId)
    }

    fun getProduct(productId: Int?) {
        productId?.let {
            viewModelScope.launch(exceptionHandler) {
                _uiState.value = _uiState.value.copy(loading = true, showError = false)
                getProductUseCase(productId).data?.let {
                    _uiState.value =
                        _uiState.value.copy(productModel = it, loading = false, showError = false)
                } ?: run {
                    showError()
                }
            }
        } ?: run {
            showError()
        }
    }

    private fun showError() {
        _uiState.value = _uiState.value.copy(showError = true, loading = false)
    }
}