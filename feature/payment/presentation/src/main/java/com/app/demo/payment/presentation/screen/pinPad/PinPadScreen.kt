package com.app.demo.payment.presentation.screen.pinPad

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.demo.payment.presentation.R
import com.app.demo.payment.presentation.components.AmountInput
import com.app.demo.payment.presentation.components.PinPad
import com.app.demo.payment.presentation.components.PinPadLoader
import com.app.demo.ui.theme.DemoTheme

@Composable
fun PinPadScreen() {
    val viewModel = hiltViewModel<PinPadViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            if (event is PinPadViewModel.UiEvent.Vibrate) {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            }
        }
    }

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
    val amount = remember { derivedStateOf { state.value.amount } }
    val isLoading = remember { derivedStateOf { state.value.isLoading } }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(76.dp))
        Text(
            text = stringResource(R.string.purchase),
            style = DemoTheme.typography.title,
            color = DemoTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.purchase_hint),
            style = DemoTheme.typography.subtitle,
            color = DemoTheme.colors.textSecondary
        )
        Spacer(modifier = Modifier.height(60.dp))
        AmountInput(
            amount = amount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(140.dp))
        Box {
            PinPad(
                modifier = Modifier.fillMaxWidth(),
                onPadClick = { onIntent(PinPadViewModel.UiIntent.OnPadClicked(it)) },
                onBackspaceClick = { onIntent(PinPadViewModel.UiIntent.OnBackspaceClicked) },
                onBackspaceLongPressed = { onIntent(PinPadViewModel.UiIntent.OnBackspaceLongPressed) },
                onBackspaceLongPressReleased = { onIntent(PinPadViewModel.UiIntent.OnBackspaceLongPressReleased) },
                onOkClick = { onIntent(PinPadViewModel.UiIntent.OnOkClicked) }
            )
            PinPadLoader(
                isLoading = isLoading,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "PinPadContent Preview - With Amount"
)
@Composable
fun PreviewPinPadContentWithAmount() {
    DemoTheme {
        val dummyState = remember { mutableStateOf(PinPadViewModel.UiState(amount = "123.45")) }
        PinPadContent(
            state = dummyState,
            onIntent = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "PinPadContent Preview - Empty Amount"
)
@Composable
fun PreviewPinPadContentEmptyAmount() {
    DemoTheme {
        val dummyState = remember { mutableStateOf(PinPadViewModel.UiState(amount = "")) }
        PinPadContent(
            state = dummyState,
            onIntent = {}
        )
    }
}