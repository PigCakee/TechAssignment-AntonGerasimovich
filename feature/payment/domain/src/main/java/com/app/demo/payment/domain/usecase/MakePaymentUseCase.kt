package com.app.demo.payment.domain.usecase

import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.domain.repository.PaymentRepository
import java.math.BigDecimal
import javax.inject.Inject

class MakePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(amount: BigDecimal): Result<Transaction?> {
        // Theoretically might do extra work. Some might argue this use case is unnecessary.
        // Added for demo purposes.
        return paymentRepository.makePayment(amount)
    }
}