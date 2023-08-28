package `in`.mrkaydev.dhyaan.utils

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.ui.components.MusicPlayerData

object Utils {
    val musicList = listOf(
        MusicPlayerData("Song Title", "Song Description", "ocean"),
        MusicPlayerData("Song Title", "Song Description", "desert"),
        MusicPlayerData("Song Title", "Song Description", "mountain"),
        MusicPlayerData("Song Title", "Song Description", "snow"),
        MusicPlayerData("Song Title", "Song Description", "tokiyo")
    )
    val buttonWidth = if (platform == "web" || platform == "desktop") 240.dp else 120.dp
    val verticalSpacer = if (platform == "web" || platform == "desktop") 56.dp else 24.dp
    val settingSize = if (platform == "web" || platform == "desktop") 24.dp else 20.dp
    val headerTitleTextSize = if (platform == "web" || platform == "desktop") 32.sp else 24.sp
    val headerDevTitleTextSize = if (platform == "web" || platform == "desktop") 18.sp else 16.sp
    val buttonTextSize = if (platform == "web" || platform == "desktop") 24.sp else 18.sp
    val timerTextSize = if (platform == "web" || platform == "desktop") 80.sp else 48.sp
}