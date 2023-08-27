package `in`.mrkaydev.dhyaan.utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import org.jetbrains.compose.resources.resource

object FontLoader {
    private const val fontPath = "fonts"
    var appFont: FontFamily? = null
        private set

    suspend fun loadFontsAsync() {
        appFont = FontFamily(
            Font(
                identity = "bold",
                data = resource("$fontPath/bold.ttf").readBytes(),
                weight = FontWeight.Bold
            ),
            Font(
                identity = "light",
                data = resource("$fontPath/light.ttf").readBytes(),
                weight = FontWeight.Light
            ),
            Font(
                identity = "medium",
                data = resource("$fontPath/medium.ttf").readBytes(),
                weight = FontWeight.Medium
            ),
            Font(
                identity = "regular",
                data = resource("$fontPath/regular.ttf").readBytes(),
                weight = FontWeight.Normal
            ),
        )
    }
}