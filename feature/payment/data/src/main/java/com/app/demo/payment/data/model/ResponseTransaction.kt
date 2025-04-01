package com.app.demo.payment.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTransaction(
    @SerialName("transactionId")
    val transactionId: String,
    @SerialName("status")
    val status: String,
    @SerialName("amount")
    val amount: ResponseAmount,
    @SerialName("transactionDetails")
    val transactionDetails: ResponseTransactionDetails
)

@Serializable
data class ResponseAmount(
    @SerialName("purchaseAmount")
    val purchaseAmount: String,
    @SerialName("currency")
    val currency: String,
    @SerialName("taxableAmount")
    val taxableAmount: String,
    @SerialName("taxRate")
    val taxRate: String,
    @SerialName("tipAmount")
    val tipAmount: String,
    @SerialName("discountAmount")
    val discountAmount: String
)

@Serializable
data class ResponseTransactionDetails(
    @SerialName("timestamp")
    val timestamp: String
)