package com.app.demo.payment.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class ReceiptUiModel(
    val transactionId: String,
    val status: String,
    val finalAmount: String,
    val currency: String,
    val tax: String,
    val date: String,
)