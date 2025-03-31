package com.app.demo.payment.presentation.navigation

import androidx.annotation.Keep
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.app.demo.navigation.api.serializableType
import com.app.demo.payment.presentation.model.ReceiptUiModel
import com.app.demo.payment.presentation.screen.pinPad.PinPadScreen
import com.app.demo.payment.presentation.screen.receipt.ReceiptBottomSheet
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
@Keep
data class ReceiptDialog(
    val receiptUiModel: ReceiptUiModel
)

internal val receiptTypeMap = mapOf(
    typeOf<ReceiptUiModel>() to serializableType<ReceiptUiModel>(isNullableAllowed = false),
)

@Serializable
@Keep
data object PinPad

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.paymentNavGraph(
    navController: NavController
) {
    composable<PinPad> {
        PinPadScreen()
    }
    dialog<ReceiptDialog>(
        typeMap = receiptTypeMap,
        dialogProperties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) { backStackEntry ->
        val backStackEntryRoute = backStackEntry.toRoute<ReceiptDialog>()
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val coroutineScope = rememberCoroutineScope()
        ReceiptBottomSheet(
            receiptUiModel = backStackEntryRoute.receiptUiModel,
            sheetState = sheetState,
            onDismiss = {
                coroutineScope.launch {
                    navController.popBackStack()
                    sheetState.hide()
                }
            }
        )
    }
}