package com.app.demo.payment.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.demo.payment.presentation.R
import com.app.demo.payment.presentation.model.Pad
import com.app.demo.ui.theme.DemoTheme

val ButtonHeight = 120.dp

@Composable
internal fun PinPad(
    modifier: Modifier = Modifier,
    onPadClick: (Pad) -> Unit,
    onBackspaceClick: () -> Unit,
    onBackspaceLongPressed: () -> Unit,
    onBackspaceLongPressReleased: () -> Unit,
    onOkClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = DemoTheme.colors.pinPad)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PinPadRow(
            leftButton = {
                PinPadButton(
                    text = Pad.ONE.symbol.toString(),
                    onClick = { onPadClick(Pad.ONE) }
                )
            },
            centerButton = {
                PinPadButton(
                    text = Pad.TWO.symbol.toString(),
                    onClick = { onPadClick(Pad.TWO) }
                )
            },
            rightButton = {
                PinPadButton(
                    text = Pad.THREE.symbol.toString(),
                    onClick = { onPadClick(Pad.THREE) }
                )
            },
        )
        PinPadRow(
            leftButton = {
                PinPadButton(
                    text = Pad.FOUR.symbol.toString(),
                    onClick = { onPadClick(Pad.FOUR) }
                )
            },
            centerButton = {
                PinPadButton(
                    text = Pad.FIVE.symbol.toString(),
                    onClick = { onPadClick(Pad.FIVE) }
                )
            },
            rightButton = {
                PinPadButton(
                    text = Pad.SIX.symbol.toString(),
                    onClick = { onPadClick(Pad.SIX) }
                )
            },
        )
        PinPadRow(
            leftButton = {
                PinPadButton(
                    text = Pad.SEVEN.symbol.toString(),
                    onClick = { onPadClick(Pad.SEVEN) }
                )
            },
            centerButton = {
                PinPadButton(
                    text = Pad.EIGHT.symbol.toString(),
                    onClick = { onPadClick(Pad.EIGHT) }
                )
            },
            rightButton = {
                PinPadButton(
                    text = Pad.NINE.symbol.toString(),
                    onClick = { onPadClick(Pad.NINE) }
                )
            },
        )
        PinPadRow(
            leftButton = {
                Backspace(
                    onClick = onBackspaceClick,
                    onLongPress = onBackspaceLongPressed,
                    onLongPressRelease = onBackspaceLongPressReleased
                )
            },
            centerButton = {
                PinPadButton(
                    text = Pad.ZERO.symbol.toString(),
                    onClick = { onPadClick(Pad.ZERO) }
                )
            },
            rightButton = {
                OkButton(onClick = onOkClick)
            },
        )
    }
}

@Composable
private fun ColumnScope.PinPadRow(
    leftButton: @Composable (RowScope.() -> Unit),
    centerButton: @Composable (RowScope.() -> Unit),
    rightButton: @Composable (RowScope.() -> Unit),
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        leftButton()
        centerButton()
        rightButton()
    }
}

@Composable
private fun RowScope.PinPadButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = DemoTheme.colors.textSecondary,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .weight(1f)
            .height(ButtonHeight)
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable { onClick() },
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = DemoTheme.typography.pinPad,
            color = textColor,
        )
    }
}

@Composable
private fun RowScope.Backspace(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongPress: () -> Unit,
    onLongPressRelease: () -> Unit
) {
    Box(
        modifier = modifier
            .weight(1f)
            .height(ButtonHeight)
            .clip(shape = RoundedCornerShape(16.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onLongPress = { onLongPress() },
                    onPress = {
                        tryAwaitRelease()
                        onLongPressRelease()
                    }
                )
            },
    ) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_clear_num),
            contentDescription = "Clear",
        )
    }
}

@Composable
private fun RowScope.OkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    PinPadButton(
        modifier = modifier.background(
            color = DemoTheme.colors.buttonPrimary,
            shape = RoundedCornerShape(16.dp)
        ),
        text = stringResource(id = R.string.ok),
        textColor = DemoTheme.colors.surface,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewPinPad() {
    DemoTheme {
        PinPad(
            onPadClick = {},
            onBackspaceClick = {},
            onBackspaceLongPressed = {},
            onBackspaceLongPressReleased = {},
            onOkClick = {}
        )
    }
}