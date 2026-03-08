package org.ceaser.e2ee.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val SpartanColorPalette = lightColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = GrayDark,
    onSecondary = White,
    background = White,
    onBackground = Black,
    surface = GrayLight,
    onSurface = Black
)

@Composable
fun JetpackComposeBoilerplateTheme(
    darkTheme: Boolean = false, // Force light theme for spartan style
    content: @Composable () -> Unit
) {
    val colorScheme = SpartanColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}