package com.app.demo.payment.data.mapper

import com.app.demo.payment.data.model.ResponseTransaction
import com.app.demo.payment.domain.model.Transaction

fun ResponseTransaction.toDomain() = Transaction(
    transactionId = transactionId,
    status = status,
    purchaseAmount = amount.purchaseAmount,
    tipAmount = amount.tipAmount,
    currency = amount.currency,
    taxableAmount = amount.taxableAmount,
    taxRate = amount.taxRate,
    discountAmount = amount.discountAmount,
    timestamp = transactionDetails.timestamp
)