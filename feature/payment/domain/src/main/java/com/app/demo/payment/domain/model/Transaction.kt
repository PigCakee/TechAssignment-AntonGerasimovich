package com.app.demo.payment.domain.model

data class Transaction(
    val transactionId: String,
    val status: String,
    val purchaseAmount: String,
    val currency: String,
    val taxableAmount: String,
    val taxRate: String,
    val tipAmount: String,
    val discountAmount: String,
    val timestamp: String
)
