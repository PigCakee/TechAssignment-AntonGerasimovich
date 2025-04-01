package com.app.demo.payment.domain.usecase

import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.domain.repository.PaymentRepository
import kotlinx.coroutines.delay
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Use case for making a payment.
 *
 * This use case encapsulates the logic for making a payment, interacting with the [PaymentRepository]
 * to persist the transaction. It also includes a simulated delay to represent a potentially
 * long-running operation.
 *
 *  **Note:** While this use case might seem simple and some may consider it unnecessary, it serves as
 * a demonstration of how use cases can be implemented and used to encapsulate business logic,
 * potentially adding more complex logic in the future (e.g., validation, pre-processing).
 *
 * @param paymentRepository The repository responsible for handling payment operations.
 */
class MakePaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository
) {
    suspend operator fun invoke(amount: BigDecimal): Result<Transaction?> {
        delay(300)
        return paymentRepository.makePayment(amount)
    }
}