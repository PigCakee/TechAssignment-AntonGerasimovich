package com.app.demo.payment.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.demo.ui.component.Loader
import com.app.demo.ui.theme.DemoTheme

@Composable
internal fun PinPadLoader(
    isLoading: State<Boolean>,
    modifier: Modifier = Modifier
) {
    if (isLoading.value) {
        Box(
            modifier = modifier.background(color = DemoTheme.colors.scrim)
        ) {
            Loader(
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            )
        }
    }
}