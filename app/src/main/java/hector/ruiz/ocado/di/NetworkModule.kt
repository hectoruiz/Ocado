package hector.ruiz.ocado.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.data.repositories.ProductRepositoryImpl
import hector.ruiz.domain.repositories.ProductRepository
import hector.ruiz.network.datasource.NetworkDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkDataSource(networkDataSourceImpl: NetworkDataSourceImpl): NetworkDataSource

    @Binds
    abstract fun bindsUProductRepository(ProductRepositoryImpl: ProductRepositoryImpl): ProductRepository
}