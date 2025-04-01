package com.app.demo.payment.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.demo.ui.theme.DemoTheme

@Composable
internal fun AmountInput(
    amount: State<String>,
    modifier: Modifier = Modifier,
) {
    Text(
        text = amount.value,
        style = DemoTheme.typography.pinPadWindow,
        color = DemoTheme.colors.textPrimary,
        textAlign = TextAlign.Center,
        modifier = modifier.border(
            border = BorderStroke(width = 1.dp, color = DemoTheme.colors.uiBorder),
            shape = RoundedCornerShape(16.dp)
        )
            .padding(16.dp)
    )
}

@Preview(name = "Default Amount", showBackground = true)
@Composable
private fun PreviewAmountInputDefault() {
    DemoTheme {
        AmountInput(amount = remember { mutableStateOf("0.00") })
    }
}

@Preview(name = "Large Amount", showBackground = true)
@Composable
private fun PreviewAmountInputLarge() {
    DemoTheme {
        AmountInput(amount = remember { mutableStateOf("12345.67") })
    }
}

@Preview(name = "Empty Amount", showBackground = true)
@Composable
private fun PreviewAmountInputEmpty() {
    DemoTheme {
        AmountInput(amount =remember { mutableStateOf( "") })
    }
}