package com.app.demo.payment.domain.repository

import com.app.demo.payment.domain.model.Transaction
import java.math.BigDecimal

/**
 * Repository interface for handling payment transactions.
 */
interface PaymentRepository {
    suspend fun makePayment(amount: BigDecimal): Result<Transaction?>
}