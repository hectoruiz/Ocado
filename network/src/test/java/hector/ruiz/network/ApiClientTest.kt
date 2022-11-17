package hector.ruiz.network

import hector.ruiz.network.api.ApiClient
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiClientTest {

    private val apiClient by lazy {
        ApiClient()
    }

    @Test
    fun `check interceptors on okHttpClient`() {
        with(apiClient) {
            assertEquals(1, this.okHttpClient.interceptors.size)
        }
    }

    @Test
    fun `check timeout configuration on okHttpClient`() {
        with(apiClient) {
            assertEquals(TIMEOUT, this.okHttpClient.connectTimeoutMillis.toLong())
            assertEquals(TIMEOUT, this.okHttpClient.readTimeoutMillis.toLong())
        }
    }

    @Test
    fun `check okHttpClient configuration on retrofit`() {
        assertEquals(apiClient.okHttpClient, apiClient.retrofit.callFactory())
    }

    @Test
    fun `check baseUrl configuration on retrofit`() {
        assertEquals(BASE_URL, apiClient.retrofit.baseUrl().toString())
    }

    private companion object {
        const val TIMEOUT = 20L * 1000
        const val BASE_URL = "https://my-json-server.typicode.com/ocadotechnology/mobile-challenge/"
    }
}