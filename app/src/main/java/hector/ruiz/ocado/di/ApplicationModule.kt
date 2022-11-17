package hector.ruiz.ocado.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.network.api.ApiClient
import hector.ruiz.network.api.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providerApiClient(): ApiClient {
        return ApiClient()
    }

    @Provides
    @Singleton
    fun providerRetrofit(apiClient: ApiClient): Retrofit {
        return apiClient.retrofit
    }

    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit): ApiService {
        return retrofit.create()
    }

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}