package com.app.demo.payment.presentation.mapper

import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.presentation.model.ReceiptUiModel
import com.app.demo.utility.date.DateTimeUtils

fun Transaction.toUiModel() = ReceiptUiModel(
    transactionId = transactionId,
    status = status,
    purchaseAmount = purchaseAmount,
    currency = currency,
    taxableAmount = taxableAmount,
    taxRate = taxRate,
    tipAmount = tipAmount,
    discountAmount = discountAmount,
    timestamp = DateTimeUtils.convertApiDateToPrettyDate(timestamp)
)