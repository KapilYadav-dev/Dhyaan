package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronLeft
import compose.icons.feathericons.ChevronRight
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.theme.colorPlayerBack
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.ui.HomeViewModel
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import org.jetbrains.compose.resources.painterResource

@Composable
fun MusicPlayer(
    modifier: Modifier,
    musicDataList: List<MusicPlayerData>,
    viewModel : HomeViewModel,
    index: (Int) -> Unit = {}
) {
    var isPlaying by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableStateOf(0) }
    val musicState = musicDataList[currentIndex]
    val maxChars = 20
    val title = if (musicState.title.length > maxChars) {
        "${musicState.title.substring(0, maxChars)}..."
    } else {
        musicState.title
    }
    val desc = if (musicState.description.length > maxChars) {
        "${musicState.description.substring(0, maxChars)}..."
    } else {
        musicState.description
    }

    Box(
        modifier = modifier.padding(16.dp).background(
            color = colorPlayerBack,
            shape = RoundedCornerShape(size = 12.dp)
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(72.dp).padding(12.dp).clip(RoundedCornerShape(12.dp)),
                painter = painterResource("images/${musicState.image}.jpeg"),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Column {
                Text(
                    title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontLoader.appFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                    )
                )
                Text(
                    text = desc,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontLoader.appFont,
                        fontWeight = FontWeight.Light,
                        color = Color(0x59FFFFFF),
                    )
                )
            }
        }
        Row(
            Modifier.padding(end = 24.dp).align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = FeatherIcons.ChevronLeft,
                "left",
                tint = colorWhite,
                modifier = Modifier.size(32.dp).clickable {
                    currentIndex = (currentIndex - 1 + musicDataList.size) % musicDataList.size
                    isPlaying = true
                    index(currentIndex)
                    viewModel.playAudio(currentIndex)
                }
            )
            Image(
                modifier = Modifier.size(40.dp)
                    .clickable {
                        isPlaying = !isPlaying
                        if(isPlaying && viewModel.isAnyAudioPlaying) {
                            viewModel.resumeAudio()
                        } else if(isPlaying) {
                            viewModel.isAnyAudioPlaying=true
                            viewModel.playAudio(0)
                        } else {
                            viewModel.pauseAudio()
                        }
                    },
                painter = painterResource("images/${if (isPlaying) "pause" else "play"}.${if (platform == Constants.WEB) "png" else "xml"}"),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Icon(
                imageVector = FeatherIcons.ChevronRight,
                "right",
                tint = colorWhite,
                modifier = Modifier.size(32.dp).clickable {
                    currentIndex = (currentIndex + 1) % musicDataList.size
                    isPlaying = true
                    index(currentIndex)
                    viewModel.playAudio(currentIndex)
                }
            )
        }

    }
}

data class MusicPlayerData(
    val title: String = "music",
    val description: String = "by unknown",
    val image: String? = null,
    val audio: String? = null,
    val androidId: String? = null,
)