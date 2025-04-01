package com.app.demo.presentation.navigation

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.demo.navigation.api.NavigationCommand
import com.app.demo.navigation.api.NavigationManager
import com.app.demo.payment.presentation.navigation.PinPad
import com.app.demo.payment.presentation.navigation.paymentNavGraph

@Composable
@SuppressLint("RestrictedApi")
internal fun MainNavHost(
    navController: NavHostController,
    navigationManager: NavigationManager,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.then(modifier),
    ) {
        NavHost(
            navController = navController,
            startDestination = PinPad,
            builder = {
                paymentNavGraph(navController)
                // can add more graphs here
            }
        )
    }

    LaunchedEffect(Unit) {
        navigationManager.navigationCommandFlow.collect { command ->
            when (command) {
                is NavigationCommand.Forward -> {
                    navController.navigate(command.destination)
                }

                is NavigationCommand.ShowToast -> {
                    Toast.makeText(context, command.title.asString(context), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        navController.currentBackStack.collect { backStack ->
            Log.d(
                "Navigation graph",
                backStack.joinToString(" -> ") {
                    it.destination
                        .route
                        ?.split(".")
                        ?.lastOrNull()
                        ?: "Empty"
                }
            )
        }
    }
}
