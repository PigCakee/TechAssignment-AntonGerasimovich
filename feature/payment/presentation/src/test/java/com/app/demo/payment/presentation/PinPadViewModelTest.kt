package com.app.demo.payment.presentation

import app.cash.turbine.test
import com.app.demo.navigation.api.NavigationManager
import com.app.demo.navigation.api.forwardTo
import com.app.demo.navigation.api.showToast
import com.app.demo.network.ext.toUiText
import com.app.demo.payment.domain.model.Transaction
import com.app.demo.payment.domain.usecase.MakePaymentUseCase
import com.app.demo.payment.presentation.mapper.toReceiptUiModel
import com.app.demo.payment.presentation.model.Pad
import com.app.demo.payment.presentation.navigation.ReceiptDialog
import com.app.demo.payment.presentation.screen.pinPad.PinPadViewModel
import com.app.demo.payment.presentation.screen.pinPad.PinPadViewModel.UiEvent.Vibrate
import com.app.demo.payment.presentation.screen.pinPad.PinPadViewModel.UiIntent
import com.app.demo.ui.model.UiText
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PinPadViewModelTest {

    private lateinit var viewModel: PinPadViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var navigationManager: NavigationManager
    private lateinit var makePaymentUseCase: MakePaymentUseCase

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        navigationManager = mockk(relaxed = true)
        makePaymentUseCase = mockk()
        viewModel = PinPadViewModel(
            navigationManager = navigationManager,
            makePaymentUseCase = makePaymentUseCase
        )
    }

    @AfterEach
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Nested
    inner class StateTests {

        @Test
        fun `initial state should be 0_00`() = runTest {
            assertThat(viewModel.state.value.amount).isEqualTo("0.00")
        }

        @Test
        fun `onPadClicked with ONE from 0_00 updates state to 0_01`() = runTest {
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
            assertThat(viewModel.state.value.amount).isEqualTo("0.01")
        }

        @Test
        fun `onPadClicked with ZERO when state is 0_00 does not change state`() = runTest {
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ZERO))
            assertThat(viewModel.state.value.amount).isEqualTo("0.00")
        }

        @Test
        fun `onPadClicked with sequence ONE, TWO, THREE updates state cumulatively`() = runTest {
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
            assertThat(viewModel.state.value.amount).isEqualTo("0.01")
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.TWO))
            assertThat(viewModel.state.value.amount).isEqualTo("0.12")
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.THREE))
            assertThat(viewModel.state.value.amount).isEqualTo("1.23")
        }

        @Test
        fun `onBackspaceClicked removes the last digit from the amount`() = runTest {
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.TWO))
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.THREE))
            assertThat(viewModel.state.value.amount).isEqualTo("1.23")
            viewModel.acceptIntent(UiIntent.OnBackspaceClicked)
            assertThat(viewModel.state.value.amount).isEqualTo("0.12")
        }

        @Test
        fun `onBackspaceLongPress continuously deletes digits until state reaches 0_00`() =
            runTest {
                viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
                viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.TWO))
                viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.THREE))
                assertThat(viewModel.state.value.amount).isEqualTo("1.23")
                // Start long press backspace
                viewModel.acceptIntent(UiIntent.OnBackspaceLongPressed)
                advanceTimeBy(350L) // allow several deletion iterations
                viewModel.acceptIntent(UiIntent.OnBackspaceLongPressReleased)
                advanceUntilIdle()
                assertThat(viewModel.state.value.amount).isEqualTo("0.00")
            }

        @Test
        fun `onOkClicked with zero amount shows error toast`() = runTest {
            viewModel.acceptIntent(UiIntent.OnOkClicked)
            advanceUntilIdle()
            coVerify {
                navigationManager.showToast(UiText.Id(R.string.error_amount_zero))
            }
        }

        @Test
        fun `onOkClicked with non-zero amount navigates on successful payment`() = runTest {
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.TWO))
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.THREE))
            
            val transaction = Transaction(
                transactionId = "tx123",
                status = "Success",
                purchaseAmount = "1.23",
                currency = "USD",
                taxableAmount = "1.23",
                taxRate = "0.10",
                tipAmount = "0.10",
                discountAmount = "0.05",
                timestamp = "2025-01-24T12:30:00Z"
            )
            coEvery { makePaymentUseCase.invoke(any()) } returns Result.success(transaction)
            viewModel.acceptIntent(UiIntent.OnOkClicked)
            advanceUntilIdle()
            coVerify { navigationManager.forwardTo(ReceiptDialog(transaction.toReceiptUiModel())) }
            assertThat(viewModel.state.value.isLoading).isFalse()
        }

        @Test
        fun `onOkClicked with non-zero amount shows error toast on failure`() = runTest {
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.TWO))
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.THREE))

            coEvery { makePaymentUseCase.invoke(any()) } returns Result.failure(Exception())
            viewModel.acceptIntent(UiIntent.OnOkClicked)
            advanceUntilIdle()
            coVerify {
                navigationManager.showToast(Exception().toUiText())
            }
            assertThat(viewModel.state.value.isLoading).isFalse()
        }
    }

    @Nested
    inner class UiEventTests {

        @Test
        fun `onPadClicked with ONE emits Vibrate event`() = runTest {
            viewModel.uiEvent.test {
                viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
                assertThat(awaitItem()).isEqualTo(Vibrate)
                cancelAndConsumeRemainingEvents()
            }
        }

        @Test
        fun `onBackspaceClicked emits Vibrate event`() = runTest {
            viewModel.acceptIntent(UiIntent.OnPadClicked(Pad.ONE))
            viewModel.uiEvent.test {
                viewModel.acceptIntent(UiIntent.OnBackspaceClicked)
                assertThat(awaitItem()).isEqualTo(Vibrate)
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}