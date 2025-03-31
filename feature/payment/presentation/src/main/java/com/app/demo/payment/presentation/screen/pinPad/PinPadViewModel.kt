package com.app.demo.payment.presentation.screen.pinPad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.demo.navigation.api.NavigationManager
import com.app.demo.navigation.api.showToast
import com.app.demo.payment.presentation.R
import com.app.demo.payment.presentation.model.Pad
import com.app.demo.ui.model.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PinPadViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>(extraBufferCapacity = 1)
    val uiEvent = _uiEvent.asSharedFlow()

    private var backspaceJob: Job? = null

    fun acceptIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.OnPadClicked -> onPadClicked(intent.pad)
            is UiIntent.OnBackspaceClicked -> onBackspaceClicked()
            is UiIntent.OnBackspaceLongPressed -> startBackspaceLongPress()
            is UiIntent.OnBackspaceLongPressReleased -> stopBackspaceLongPress()
            is UiIntent.OnOkClicked -> onOkClicked()
        }
    }

    private fun onOkClicked() {
        if (state.value.amount == DEFAULT_AMOUNT) {
            navigationManager.showToast(UiText.Id(R.string.error_amount_zero))
        } else {
            // todo add payment logic
        }
    }

    private fun onBackspaceClicked() {
        _state.update { state ->
            val currentAmount = state.amount
                .replace(",", "")
                .toBigDecimalOrNull()
                ?.setScale(DECIMAL_PLACES) ?: BigDecimal.ZERO.setScale(DECIMAL_PLACES)
            val currentCents = currentAmount.movePointRight(DECIMAL_PLACES)
            val newCents = currentCents.divideToIntegralValue(BigDecimal.TEN)
            val newAmount = newCents
                .movePointLeft(DECIMAL_PLACES)
                .setScale(DECIMAL_PLACES, RoundingMode.UNNECESSARY)
            state.copy(amount = formatAmount(newAmount))
        }
        vibrate()
    }

    private fun onPadClicked(pad: Pad) {
        _state.update { state ->
            val currentAmount = state.amount
                .replace(",", "")
                .toBigDecimalOrNull()
                ?.setScale(DECIMAL_PLACES) ?: BigDecimal.ZERO.setScale(DECIMAL_PLACES)
            val currentCents = currentAmount.movePointRight(DECIMAL_PLACES)
            if (pad.symbol == '0' && currentCents == BigDecimal.ZERO) {
                state
            } else {
                val digit = pad.symbol.digitToIntOrNull() ?: return@update state
                val newCents = currentCents.multiply(BigDecimal.TEN).add(BigDecimal(digit))
                val maxCents = BigDecimal.TEN.pow(INTEGER_PART_LENGTH)
                    .multiply(BigDecimal(100))
                    .subtract(BigDecimal.ONE)
                if (newCents > maxCents) {
                    state
                } else {
                    val newAmount = newCents
                        .movePointLeft(DECIMAL_PLACES)
                        .setScale(DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                    state.copy(amount = formatAmount(newAmount))
                }
            }
        }
        vibrate()
    }

    private fun startBackspaceLongPress() {
        if (backspaceJob?.isActive == true) return
        backspaceJob = viewModelScope.launch {
            while (true) {
                onBackspaceClicked()
                if (state.value.amount == DEFAULT_AMOUNT) break
                delay(100L)
            }
        }
    }

    private fun stopBackspaceLongPress() {
        backspaceJob?.cancel()
        backspaceJob = null
    }

    private fun vibrate() {
        viewModelScope.launch { _uiEvent.emit(UiEvent.Vibrate) }
    }

    private fun formatAmount(amount: BigDecimal): String {
        val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
            minimumFractionDigits = DECIMAL_PLACES
            maximumFractionDigits = DECIMAL_PLACES
        }
        return formatter.format(amount)
    }

    sealed interface UiIntent {
        data class OnPadClicked(val pad: Pad) : UiIntent
        data object OnBackspaceClicked : UiIntent
        data object OnBackspaceLongPressed : UiIntent
        data object OnBackspaceLongPressReleased : UiIntent
        data object OnOkClicked : UiIntent
    }

    sealed interface UiEvent {
        data object Vibrate : UiEvent
    }

    data class UiState(
        val amount: String = DEFAULT_AMOUNT,
    )

    companion object {
        const val INTEGER_PART_LENGTH = 10
        const val DECIMAL_PLACES = 2
        const val DEFAULT_AMOUNT = "0.00"
    }
}