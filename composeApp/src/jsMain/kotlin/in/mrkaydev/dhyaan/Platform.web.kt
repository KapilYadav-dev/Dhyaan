package `in`.mrkaydev.dhyaan

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import `in`.mrkaydev.dhyaan.utils.Constants
import kotlinx.browser.window
import org.jetbrains.compose.resources.resource

internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}

internal actual val platform: String
    get() = Constants.WEB

internal actual suspend fun loadPlatformFonts(): FontFamily {
    val fontPath = "fonts"
    return FontFamily(
        Font(
            identity = "bold",
            data = resource("${fontPath}/bold.ttf").readBytes(),
            weight = FontWeight.Bold
        ),
        Font(
            identity = "light",
            data = resource("${fontPath}/light.ttf").readBytes(),
            weight = FontWeight.Light
        ),
        Font(
            identity = "medium",
            data = resource("${fontPath}/medium.ttf").readBytes(),
            weight = FontWeight.Medium
        ),
        Font(
            identity = "regular",
            data = resource("${fontPath}/regular.ttf").readBytes(),
            weight = FontWeight.Normal
        ),
    )
}