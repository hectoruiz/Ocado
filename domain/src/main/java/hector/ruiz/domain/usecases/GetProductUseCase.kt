package hector.ruiz.domain.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ProductModel
import hector.ruiz.domain.repositories.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(productId: Int): ResponseResult<ProductModel> {
        val result = productRepository.getProduct(productId)
        return withContext(ioDispatcher) {
            ResponseResult(data = result.data, errorCode = result.errorCode)
        }
    }
}
