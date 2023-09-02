package `in`.mrkaydev.dhyaan

import androidx.compose.ui.text.font.FontFamily


internal expect fun openUrl(url: String?)

internal expect val platform:String
internal expect suspend fun loadPlatformFonts():FontFamily

internal expect class AudioPlayer() {
    fun playAudio(resourceFileName: String)
    fun pauseAudio()
    fun resumeAudio()
}

internal expect fun showFullScreenWebOnly()