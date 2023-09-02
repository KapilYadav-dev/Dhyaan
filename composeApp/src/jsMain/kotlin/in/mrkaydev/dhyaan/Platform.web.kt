package `in`.mrkaydev.dhyaan

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import `in`.mrkaydev.dhyaan.utils.Constants
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.resources.resource
import org.w3c.dom.Audio

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

internal actual class AudioPlayer {
    private var audioElement: Audio? = null
    actual fun playAudio(resourceFileName: String) {
        playAudioOnWeb(resourceFileName)
    }

    actual fun pauseAudio() {
        pauseAudioOnWeb()
    }

    actual fun resumeAudio() {
        resumeAudioOnWeb()
    }
    private fun playAudioOnWeb(resourceFileName: String) {
        if (audioElement != null) {
            audioElement?.pause() // Pause if audio is already playing
        }

        audioElement = Audio(resourceFileName)
        audioElement?.loop=true
        audioElement?.play()
    }

    private fun pauseAudioOnWeb() {
        if (audioElement != null) {
            audioElement?.pause()
        }
    }

    private fun resumeAudioOnWeb() {
        if (audioElement != null) {
            audioElement?.play()
        }
    }
}

internal actual fun showFullScreenWebOnly() {
    val element  = document.getElementById("ComposeTarget")
    element?.requestFullscreen()
}