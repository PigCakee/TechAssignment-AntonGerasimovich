package com.app.demo.payment.wiring

import com.app.demo.payment.data.api.PaymentWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PaymentNetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit {
        val httpClient = OkHttpClient.Builder()
        val converterFactory = json.asConverterFactory(HEADER_CONTENT_TYPE_VALUE.toMediaType())
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun providePaymentWebService(retrofit: Retrofit): PaymentWebService =
        retrofit.create(PaymentWebService::class.java)

    private const val BASE_URL = "https://jason-koala.wallee.workers.dev/"
    private const val HEADER_CONTENT_TYPE_VALUE = "application/json"
}