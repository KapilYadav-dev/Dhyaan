package `in`.mrkaydev.dhyaan.utils

import androidx.compose.ui.text.font.FontFamily
import `in`.mrkaydev.dhyaan.loadPlatformFonts

object FontLoader {
    var appFont: FontFamily? = null
        private set

    suspend fun loadFontsAsync() {
        appFont = loadPlatformFonts()
    }
}