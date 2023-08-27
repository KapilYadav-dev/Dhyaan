package `in`.mrkaydev.dhyaan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.RefreshCcw
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.ui.components.MusicPlayer
import `in`.mrkaydev.dhyaan.ui.components.SelectableButton
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.utils.Utils.musicList
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun App() {
    var isFontLoaded by remember { mutableStateOf(false) }
    var isPomodoroSelected by remember { mutableStateOf(true) }
    var isLongBreakSelected by remember { mutableStateOf(false) }
    var isShortBreakSelected by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        FontLoader.loadFontsAsync()
        isFontLoaded = true
    }
    if (isFontLoaded) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painterResource("images/desert.jpeg"),
                "bg",
                Modifier.fillMaxSize(),
                contentScale = if (platform == "android") ContentScale.Crop else ContentScale.FillBounds
            )
            Column(Modifier.padding(start = 24.dp, top = 0.dp)) {
                Text(
                    "dhyaan",
                    fontSize = 24.sp,
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Bold,
                    color = colorWhite,
                    textAlign = TextAlign.Center
                )
                Text(
                    "by mrkaydev",
                    fontSize = 16.sp,
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Light,
                    color = colorWhite,
                    textAlign = TextAlign.Center
                )
            }
            Image(painterResource("images/setting.png"), "", Modifier.padding(16.dp).size(24.dp).align(Alignment.TopEnd))
            Column(Modifier.align(Alignment.Center).fillMaxWidth()) {
                Row(
                    Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.width(32.dp))
                    SelectableButton(
                        text = "pomodoro",
                        isClicked = { isPomodoroSelected },
                        buttonClicked = {
                            if (!isPomodoroSelected) {
                                isPomodoroSelected = !isPomodoroSelected
                                isLongBreakSelected = false
                                isShortBreakSelected = false
                            }
                        })

                    Spacer(Modifier.width(16.dp))
                    SelectableButton(
                        text = "long break",
                        isClicked = { isLongBreakSelected },
                        buttonClicked = {
                            if (!isLongBreakSelected) {
                                isLongBreakSelected = !isLongBreakSelected
                                isPomodoroSelected = false
                                isShortBreakSelected = false
                            }
                        })

                    Spacer(Modifier.width(16.dp))
                    SelectableButton(
                        text = "short break",
                        isClicked = { isShortBreakSelected },
                        buttonClicked = {
                            if (!isShortBreakSelected) {
                                isShortBreakSelected = !isShortBreakSelected
                                isPomodoroSelected = false
                                isLongBreakSelected = false
                            }
                        })
                    Spacer(Modifier.width(32.dp))
                }
                Spacer(Modifier.height(24.dp))
                Text(
                    "45:00",
                    fontSize = 48.sp,
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Bold,
                    color = colorWhite,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(24.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SelectableButton(text = "start", isClicked = { true }, buttonClicked = {})
                    Spacer(modifier = Modifier.width(32.dp))
                    Icon(
                        FeatherIcons.RefreshCcw,
                        "",
                        tint = colorWhite,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            MusicPlayer(
                modifier = Modifier.fillMaxWidth(if (platform == "android" || platform == "ios") 1f else 0.25f)
                    .align(Alignment.BottomStart),
                musicList
            )
        }
    }
}