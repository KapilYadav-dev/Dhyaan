package `in`.mrkaydev.dhyaan.utils

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.ui.components.MusicPlayerData

object Utils {
    const val APP_NAME: String = "Dhyaan"
    const val ANDROID: String = "android"
    const val IOS: String = "ios"
    const val WEB: String = "web"
    const val DESKTOP: String = "desktop"
    const val POMODORO_TIME: Int = 45

    val buttonWidth = if (platform == WEB || platform == Utils.DESKTOP) 240.dp else 120.dp
    val verticalSpacer = if (platform == WEB || platform == Utils.DESKTOP) 56.dp else 24.dp
    val settingSize = if (platform == WEB || platform == Utils.DESKTOP) 24.dp else 20.dp
    val headerTitleTextSize = if (platform == WEB || platform == Utils.DESKTOP) 32.sp else 24.sp
    val headerDevTitleTextSize = if (platform == WEB || platform == Utils.DESKTOP) 18.sp else 16.sp
    val buttonTextSize = if (platform == WEB || platform == Utils.DESKTOP) 24.sp else 18.sp
    val timerTextSize = if (platform == WEB || platform == Utils.DESKTOP) 80.sp else 48.sp
    val musicList = listOf(
        MusicPlayerData("Song Title", "Song Description", "ocean"),
        MusicPlayerData("Song Title", "Song Description", "desert"),
        MusicPlayerData("Song Title", "Song Description", "mountain"),
        MusicPlayerData("Song Title", "Song Description", "snow"),
        MusicPlayerData("Song Title", "Song Description", "tokiyo")
    )
}