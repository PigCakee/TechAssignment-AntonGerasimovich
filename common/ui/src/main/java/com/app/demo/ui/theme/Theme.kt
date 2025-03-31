package com.app.demo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White

private val LightColorScheme = lightColorScheme()

@Composable
fun DemoTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    ProvideDemoColors(DemoTheme.colors) {
        ProvideDemoTypography(DemoTheme.typography) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                content = content
            )
        }
    }
}

object DemoTheme {
    val colors: DemoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDemoColors.current

    val typography: DemoTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDemoTypography.current
}

@Stable
data class DemoColors(
    val buttonPrimary: Color,
    val uiBorder: Color,
    val surface: Color,
    val primary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val loader: Color,
    val pinPad: Color,
)

@Composable
fun ProvideDemoColors(
    colors: DemoColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalDemoColors provides colors, content = content)
}


private val LocalDemoColors = staticCompositionLocalOf {
    DemoColors(
        buttonPrimary = ButtonColor,
        uiBorder = BorderColor,
        surface = White,
        primary = PrimaryColor,
        textPrimary = PrimaryColor,
        textSecondary = SecondaryColor,
        loader = LoaderColor,
        pinPad = PinPadColor,
    )
}
