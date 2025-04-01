package com.app.demo.payment.presentation.screen.receipt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.demo.payment.presentation.R
import com.app.demo.payment.presentation.model.ReceiptUiModel
import com.app.demo.ui.theme.DemoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptBottomSheet(
    sheetState: SheetState,
    receiptUiModel: ReceiptUiModel,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        modifier = Modifier.statusBarsPadding(),
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = DemoTheme.colors.surface,
        contentColor = DemoTheme.colors.surface,
        dragHandle = null
    ) {
        val textColor = DemoTheme.colors.textPrimary
        val subtitleStyle = DemoTheme.typography.subtitle
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.receipt_title),
                style = DemoTheme.typography.title,
                color = textColor
            )
            Text(
                text = stringResource(
                    R.string.receipt_transaction_id,
                    receiptUiModel.transactionId
                ),
                style = subtitleStyle,
                color = textColor
            )
            Text(
                text = stringResource(R.string.receipt_status, receiptUiModel.status),
                style = subtitleStyle,
                color = textColor
            )
            Text(
                text = stringResource(
                    R.string.receipt_final_amount,
                    receiptUiModel.finalAmount,
                    receiptUiModel.currency
                ),
                style = subtitleStyle,
                color = textColor
            )
            Text(
                text = stringResource(
                    R.string.receipt_taxable_amount,
                    receiptUiModel.tax,
                    receiptUiModel.currency
                ),
                style = subtitleStyle,
                color = textColor
            )
            Text(
                text = stringResource(R.string.receipt_date, receiptUiModel.date),
                style = subtitleStyle,
                color = textColor
            )
        }
    }
}
