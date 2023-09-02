package `in`.mrkaydev.dhyaan

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import `in`.mrkaydev.dhyaan.utils.Constants

val context = AndroidApp.INSTANCE

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}

internal actual val platform: String
    get() = Constants.ANDROID

internal actual suspend fun loadPlatformFonts(): FontFamily {
    return FontFamily(
        Font(
            R.font.bold,
            weight = FontWeight.Bold
        ),
        Font(
            R.font.light,
            weight = FontWeight.Light
        ),
        Font(
            R.font.medium,
            weight = FontWeight.Medium
        ),
        Font(
            R.font.regular,
            weight = FontWeight.Normal
        ),
    )
}

internal actual class AudioPlayer actual constructor() {
    private var mediaPlayer: MediaPlayer? = null
    actual fun playAudio(resourceFileName: String) {
        playAudioOnAndroid(resourceFileName)
    }

    actual fun pauseAudio() {
        pauseAudioOnAndroid()
    }

    actual fun resumeAudio() {
        resumeAudioOnAndroid()
    }

    private fun playAudioOnAndroid(resourceFileName: String) {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
            mediaPlayer?.release()
        }
        val resID: Int = context.resources
            .getIdentifier(resourceFileName, "raw", context.packageName)
        mediaPlayer = MediaPlayer.create(context, resID).apply {
            isLooping = true
            start()
        }
    }

    private fun pauseAudioOnAndroid() {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
        }
    }

    private fun resumeAudioOnAndroid() {
        if (mediaPlayer != null) {
            mediaPlayer?.start()
        }
    }
}

internal actual fun showFullScreenWebOnly() {
}