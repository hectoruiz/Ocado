package hector.ruiz.ocado.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.network.mappers.ClusterMapper
import hector.ruiz.network.mappers.ProductMapper
import hector.ruiz.network.mappers.ProductResponseMapper

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun providerProductResponseMapper(clusterMapper: ClusterMapper): ProductResponseMapper {
        return ProductResponseMapper(clusterMapper)
    }

    @Provides
    fun providerClusterMapper(productMapper: ProductMapper): ClusterMapper {
        return ClusterMapper(productMapper)
    }

    @Provides
    fun providerProductMapper(): ProductMapper {
        return ProductMapper()
    }
}