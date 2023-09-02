package `in`.mrkaydev.dhyaan

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import `in`.mrkaydev.dhyaan.utils.Constants
import javafx.application.Platform
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import org.jetbrains.compose.resources.resource
import java.awt.Desktop
import java.net.URI

internal actual fun openUrl(url: String?) {
    val uri = url?.let { URI.create(it) } ?: return
    Desktop.getDesktop().browse(uri)
}

internal actual val platform: String
    get() = Constants.DESKTOP

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

internal actual class AudioPlayer  actual constructor(){
    private var mediaPlayer: MediaPlayer? = null

    init {
        Platform.startup {

        }
    }
    actual fun playAudio(resourceFileName: String) {

        if(mediaPlayer!=null) {
            mediaPlayer?.pause()
            mediaPlayer=null
        }
        val media = Media(javaClass.getResource("/$resourceFileName")?.toURI().toString())
        mediaPlayer = MediaPlayer(media)
        mediaPlayer?.cycleCount = MediaPlayer.INDEFINITE
        mediaPlayer?.play()
    }

    actual fun pauseAudio() {
        mediaPlayer?.pause()
    }

    actual fun resumeAudio() {
        mediaPlayer?.play()
    }
}

internal actual fun showFullScreenWebOnly() {
}