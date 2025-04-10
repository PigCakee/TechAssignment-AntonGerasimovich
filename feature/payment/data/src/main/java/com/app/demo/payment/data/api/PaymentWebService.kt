package com.app.demo.payment.data.api

import com.app.demo.payment.data.model.ResponseTransaction
import retrofit2.Response
import retrofit2.http.GET

interface PaymentWebService {
    @GET("/")
    suspend fun makePayment(): Response<ResponseTransaction>
}