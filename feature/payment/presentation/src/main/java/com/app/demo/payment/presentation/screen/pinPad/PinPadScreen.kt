package com.app.demo.payment.presentation.screen.pinPad

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PinPadScreen() {
    val viewModel = hiltViewModel<PinPadViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()

    PinPadContent(
        state = state,
        onIntent = remember { { intent -> viewModel.acceptIntent(intent) } }
    )
}

@Composable
private fun PinPadContent(
    state: State<PinPadViewModel.UiState>,
    onIntent: (PinPadViewModel.UiIntent) -> Unit
) {

}