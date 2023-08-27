package `in`.mrkaydev.dhyaan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.ui.components.MusicPlayer
import `in`.mrkaydev.dhyaan.ui.components.MusicPlayerData
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun App() {
    var isFontLoaded by remember { mutableStateOf(false) }

    var index by remember { mutableStateOf(0) }
    val musicList = listOf(
        MusicPlayerData("Song Title", "Song Description", "ocean"),
        MusicPlayerData("Song Title", "Song Description", "desert"),
        MusicPlayerData("Song Title", "Song Description", "mountain"),
        MusicPlayerData("Song Title", "Song Description", "snow"),
        MusicPlayerData("Song Title", "Song Description", "tokiyo")
    )
    LaunchedEffect(Unit) {
        FontLoader.loadFontsAsync()
        isFontLoaded = true
    }
    if (isFontLoaded) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painterResource("images/${musicList[index].image}.jpeg"),
                "bg",
                Modifier.fillMaxSize(),
                contentScale = if(platform=="android") ContentScale.Crop else ContentScale.FillBounds
            )
            MusicPlayer(
                modifier = Modifier.fillMaxWidth(if(platform=="android" || platform=="ios") 1f else 0.25f).align(Alignment.BottomStart),
                musicList
            ) {
                index = it
            }
        }
    }
}