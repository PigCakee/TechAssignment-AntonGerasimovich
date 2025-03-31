package com.app.demo.payment.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.demo.ui.theme.DemoTheme

@Composable
fun AmountInput(
    amount: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.border(
            border = BorderStroke(width = 1.dp, color = DemoTheme.colors.uiBorder),
            shape = RoundedCornerShape(16.dp)
        )
    ) {
        Text(
            text = amount,
            style = DemoTheme.typography.pinPadWindow,
            color = DemoTheme.colors.textPrimary,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(name = "Default Amount", showBackground = true)
@Composable
fun PreviewAmountInputDefault() {
    DemoTheme {
        AmountInput(amount = "0.00")
    }
}

@Preview(name = "Large Amount", showBackground = true)
@Composable
fun PreviewAmountInputLarge() {
    DemoTheme {
        AmountInput(amount = "12345.67")
    }
}

@Preview(name = "Empty Amount", showBackground = true)
@Composable
fun PreviewAmountInputEmpty() {
    DemoTheme {
        AmountInput(amount = "")
    }
}