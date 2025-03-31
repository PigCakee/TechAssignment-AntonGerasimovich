package com.app.demo.payment.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class ReceiptUiModel(
    val transactionId: String,
    val status: String,
    val amount: AmountUiModel,
    val transactionDetails: TransactionDetailsUiModel
)

@Serializable
data class AmountUiModel(
    val purchaseAmount: String,
    val currency: String,
    val taxableAmount: String,
    val taxRate: String,
    val tipAmount: String,
    val discountAmount: String
)

@Serializable
data class TransactionDetailsUiModel(
    val timestamp: String
)