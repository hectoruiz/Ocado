package hector.ruiz.ocado.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.domain.usecases.GetProductsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ListUiState(
    val clusterList: List<ClusterModel> = listOf(),
    val loading: Boolean = true,
    val showError: Boolean = false
)

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        showError()
    }

    private val _uiState = mutableStateOf(ListUiState())
    val uiState: State<ListUiState> = _uiState

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = _uiState.value.copy(loading = true, showError = false)
            getProductsUseCase().data?.let {
                if (it.isEmpty()) showError()
                else {
                    _uiState.value =
                        _uiState.value.copy(clusterList = it, loading = false, showError = false)
                }
            } ?: run {
                showError()
            }
        }
    }

    private fun showError() {
        _uiState.value = _uiState.value.copy(showError = true, loading = false)
    }
}