package com.app.demo.payment.presentation.screen.pinPad

import androidx.lifecycle.ViewModel
import com.app.demo.payment.presentation.model.Pad
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PinPadViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun acceptIntent(intent: UiIntent) {
        when (intent) {
            is UiIntent.OnPadClicked -> onPadClicked(intent.pad)
            UiIntent.OnBackspaceClicked -> onBackspaceClicked()
            UiIntent.OnOkClicked -> onOkClicked()
        }
    }

    private fun onOkClicked() {

    }

    private fun onBackspaceClicked() {
        _state.update { state ->
            state.copy(
                amount = state.amount.dropLast(1)
            )
        }
    }

    private fun onPadClicked(pad: Pad) {
        _state.update { state ->
            state.copy(
                amount = (state.amount + pad.symbol).takeIf {
                    it.length <= INTEGER_PART_LENGTH + DECIMAL_PLACES + 1
                } ?: state.amount
            )
        }
    }

    sealed interface UiIntent {
        data class OnPadClicked(val pad: Pad) : UiIntent
        data object OnBackspaceClicked : UiIntent
        data object OnOkClicked : UiIntent
    }

    data class UiState(
        val amount: String = "",
    )

    companion object {
        const val INTEGER_PART_LENGTH = 5
        const val DECIMAL_PLACES = 2
    }
}