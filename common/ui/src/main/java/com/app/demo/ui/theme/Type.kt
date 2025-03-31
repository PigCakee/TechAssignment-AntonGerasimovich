package com.app.demo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.demo.ui.R

val font = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val Typography = Typography()

@Stable
data class DemoTypography(
    val title: TextStyle,
    val subtitle: TextStyle,
    val pinPad: TextStyle,
    val pinPadWindow: TextStyle,
)

@Composable
fun ProvideDemoTypography(
    typography: DemoTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalDemoTypography provides typography, content = content)
}


private val DefaultDemoTypography = DemoTypography(
    title = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = font,
        fontWeight = FontWeight.SemiBold
    ),
    subtitle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontFamily = font,
        fontWeight = FontWeight.Normal
    ),
    pinPad = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = font,
        fontWeight = FontWeight.Medium
    ),
    pinPadWindow = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = font,
        fontWeight = FontWeight.SemiBold
    )
)

val LocalDemoTypography = staticCompositionLocalOf { DefaultDemoTypography }
