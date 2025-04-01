package com.app.demo.payment.domain.usecase

import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.delay
import java.math.BigDecimal
import javax.inject.Inject

class MakePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(amount: BigDecimal): Result<Transaction?> {
        // Theoretically might do extra work. Some might argue this use case is unnecessary.
        // Added for demo purposes.
        delay(300) // Simulate long running operation
        return paymentRepository.makePayment(amount)
    }
}