package com.app.demo.payment.data.repository

import com.app.demo.di.qualifier.IoDispatcher
import com.app.demo.network.repository.BaseRepository
import com.app.demo.payment.data.api.PaymentWebService
import com.app.demo.payment.data.mapper.toDomain
import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Repository implementation for handling payment transactions.
 */
class PaymentRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val paymentWebService: PaymentWebService
) : PaymentRepository, BaseRepository() {
    override suspend fun makePayment(amount: BigDecimal): Result<Transaction?> {
        return withContext(ioDispatcher) {
            call {
                paymentWebService.makePayment()
            }.toResultKt { responseTransaction -> responseTransaction.toDomain() }
        }
    }
}