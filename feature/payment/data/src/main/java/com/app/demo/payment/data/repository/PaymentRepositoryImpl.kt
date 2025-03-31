package com.app.demo.payment.data.repository

import com.app.demo.di.qualifier.IoDispatcher
import com.app.demo.payment.data.api.PaymentWebService
import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.math.BigDecimal
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val paymentWebService: PaymentWebService
) : PaymentRepository {
    override suspend fun makePayment(amount: BigDecimal): Result<Transaction> {
        TODO()
    }
}