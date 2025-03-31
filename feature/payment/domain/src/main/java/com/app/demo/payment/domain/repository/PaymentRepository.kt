package com.app.demo.payment.domain.repository

import com.app.demo.payment.domain.model.Transaction
import java.math.BigDecimal

interface PaymentRepository {
    suspend fun makePayment(amount: BigDecimal): Result<Transaction>
}