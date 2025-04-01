package com.app.demo.payment.presentation.mapper

import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.presentation.model.ReceiptUiModel
import com.app.demo.utility.date.DateTimeUtils
import java.math.BigDecimal
import java.math.RoundingMode

fun Transaction.toReceiptUiModel() : ReceiptUiModel {
    val purchase = purchaseAmount.toBigDecimalOrNull() ?: BigDecimal.ZERO
    val tip = tipAmount.toBigDecimalOrNull() ?: BigDecimal.ZERO
    val discount = discountAmount.toBigDecimalOrNull() ?: BigDecimal.ZERO
    val taxable = taxableAmount.toBigDecimalOrNull() ?: BigDecimal.ZERO
    val rate = taxRate.toBigDecimalOrNull() ?: BigDecimal.ZERO

    val taxValue = taxable
        .multiply(rate)
        .setScale(2, RoundingMode.HALF_UP)

    val finalValue = purchase
        .add(tip)
        .subtract(discount)
        .add(taxValue)
        .setScale(2, RoundingMode.HALF_UP)

    return ReceiptUiModel(
        transactionId = transactionId,
        status = status,
        finalAmount = finalValue.toPlainString(),
        currency = currency,
        tax = taxValue.toPlainString(),
        date = DateTimeUtils.convertApiDateToPrettyDate(timestamp)
    )
}