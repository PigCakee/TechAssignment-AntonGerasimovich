package com.app.demo.payment.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class ReceiptUiModel(
    val transactionId: String,
    val status: String,
    val purchaseAmount: String,
    val currency: String,
    val taxableAmount: String,
    val taxRate: String,
    val tipAmount: String,
    val discountAmount: String,
    val timestamp: String,
)