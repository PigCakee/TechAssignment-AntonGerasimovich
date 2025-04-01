package com.app.demo.payment.data

import com.app.demo.payment.data.api.PaymentWebService
import com.app.demo.payment.data.model.ResponseAmount
import com.app.demo.payment.data.model.ResponseTransaction
import com.app.demo.payment.data.model.ResponseTransactionDetails
import com.app.demo.payment.data.repository.PaymentRepositoryImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response
import java.math.BigDecimal

@OptIn(ExperimentalCoroutinesApi::class)
class PaymentRepositoryImplTest {

    private val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    private val paymentWebService: PaymentWebService = mockk()

    private lateinit var repository: PaymentRepositoryImpl

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = PaymentRepositoryImpl(
            ioDispatcher = testDispatcher,
            paymentWebService = paymentWebService
        )
    }

    @AfterEach
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `makePayment returns success when web service returns successful response`() = runTest {
        coEvery { paymentWebService.makePayment() } returns Response.success(createResponse())

        val result = repository.makePayment(BigDecimal("100.00"))

        assertThat(result.isSuccess).isTrue()
        val transaction = result.getOrNull()
        assertThat(transaction?.transactionId).isEqualTo("tx123")
        assertThat(transaction?.status).isEqualTo("Success")
    }

    @Test
    fun `makePayment returns error when web service returns error response`() = runTest {
        val errorResponseBody = "Internal Server Error".toResponseBody(null)
        coEvery { paymentWebService.makePayment() } returns Response.error(500, errorResponseBody)

        val result = repository.makePayment(BigDecimal("100.00"))
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `makePayment returns error when exception is thrown`() = runTest {
        coEvery { paymentWebService.makePayment() } throws RuntimeException("Network failure")

        val result = repository.makePayment(BigDecimal("100.00"))

        assertThat(result.isFailure).isTrue()
        val exception = result.exceptionOrNull()
        assertThat(exception?.message).isEqualTo("Error Network failure")
    }

    private fun createResponse() = ResponseTransaction(
        transactionId = "tx123",
        status = "Success",
        amount = ResponseAmount(
            purchaseAmount = "100.00",
            currency = "USD",
            taxableAmount = "90.00",
            taxRate = "0.10",
            tipAmount = "10.00",
            discountAmount = "5.00"
        ),
        transactionDetails = ResponseTransactionDetails(
            timestamp = "2025-01-24T12:30:00Z"
        )
    )
}