package hector.ruiz.domain.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.ClusterModel
import hector.ruiz.domain.repositories.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): ResponseResult<List<ClusterModel>> {
        val result = productRepository.getProducts()
        return withContext(ioDispatcher) {
            ResponseResult(data = result.data?.clusters ?: emptyList(),
                errorCode = result.errorCode)
        }
    }
}
