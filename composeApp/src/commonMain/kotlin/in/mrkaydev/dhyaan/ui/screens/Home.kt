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
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import compose.icons.FeatherIcons
import compose.icons.feathericons.RefreshCcw
import `in`.mrkaydev.dhyaan.data.HomeUiState
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.ui.HomeViewModel
import `in`.mrkaydev.dhyaan.ui.components.MusicPlayer
import `in`.mrkaydev.dhyaan.ui.components.SelectableButton
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit


class Home:Screen {
    override val key: ScreenKey
        get() = "homeScreen"

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { HomeViewModel() }
        val homeUiState = viewModel.homeUiState.collectAsState()
        val currentTime by viewModel.currentTime.collectAsState()

        var timerFirstStarted by remember { mutableStateOf(false) }
        var timerRunning by remember { mutableStateOf(false) }

        val initialTimeInMinutes by remember { mutableStateOf((Constants.POMODORO_TIME).minutes) }
        val initialTime by remember { mutableStateOf(initialTimeInMinutes.toLong(DurationUnit.MILLISECONDS)) }

        when (homeUiState.value) {
            HomeUiState.Loading -> {

            }
            HomeUiState.Nothing -> {

            }
            HomeUiState.Success -> {
                val seconds = currentTime / 1000
                val minutes = seconds / 60
                val formattedTime = formatTime(minutes, seconds % 60)
                Box(Modifier.fillMaxSize()) {
                    Image(
                        painterResource("images/gradient-dark.jpeg"),
                        "bg",
                        Modifier.fillMaxSize(),
                        contentScale = if (platform == Constants.ANDROID) ContentScale.Crop else ContentScale.FillBounds
                    )
                    Column(Modifier.padding(start = 24.dp, top = 48.dp)) {
                        Text(
                            Constants.APP_NAME,
                            fontSize = Constants.headerTitleTextSize,
                            fontFamily = FontLoader.appFont,
                            fontWeight = FontWeight.Bold,
                            color = colorWhite,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            "by mrkaydev",
                            fontSize = Constants.headerDevTitleTextSize,
                            fontFamily = FontLoader.appFont,
                            fontWeight = FontWeight.Light,
                            color = colorWhite,
                            textAlign = TextAlign.Center
                        )
                    }
                    Image(
                        painterResource("images/setting.png"),
                        "",
                        Modifier.padding(vertical = 64.dp, horizontal = 16.dp).size(Constants.settingSize)
                            .align(Alignment.TopEnd)
                    )
                    Column(Modifier.align(Alignment.Center).fillMaxWidth()) {
                        Row(
                            Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(Modifier.width(32.dp))
                            SelectableButton(
                                text = "pomodoro",
                                isClicked = { viewModel.isPomodoroSelected },
                                buttonClicked = {
                                    if (!viewModel.isPomodoroSelected) {
                                        viewModel.isPomodoroSelected = !viewModel.isPomodoroSelected
                                        viewModel.isLongBreakSelected = false
                                        viewModel.isShortBreakSelected = false
                                    }
                                })

                            Spacer(Modifier.width(16.dp))
                            SelectableButton(
                                text = "long break",
                                isClicked = { viewModel.isLongBreakSelected },
                                buttonClicked = {
                                    if (!viewModel.isLongBreakSelected) {
                                        viewModel.isLongBreakSelected = !viewModel.isLongBreakSelected
                                        viewModel.isPomodoroSelected = false
                                        viewModel.isShortBreakSelected = false
                                    }
                                })

                            Spacer(Modifier.width(16.dp))
                            SelectableButton(
                                text = "short break",
                                isClicked = { viewModel.isShortBreakSelected },
                                buttonClicked = {
                                    if (!viewModel.isShortBreakSelected) {
                                        viewModel.isShortBreakSelected = !viewModel.isShortBreakSelected
                                        viewModel.isPomodoroSelected = false
                                        viewModel.isLongBreakSelected = false
                                    }
                                })
                            Spacer(Modifier.width(32.dp))
                        }
                        Spacer(Modifier.height(Constants.verticalSpacer))
                        Text(
                            formattedTime,
                            fontSize = Constants.timerTextSize,
                            fontFamily = FontLoader.appFont,
                            fontWeight = FontWeight.Bold,
                            color = colorWhite,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(Constants.verticalSpacer))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SelectableButton(text = if (timerRunning) {
                                "Pause"
                            } else {
                                "Start"
                            }, isClicked = { !timerRunning }, buttonClicked = {
                                if (timerRunning) {
                                    viewModel.pauseTimer()
                                } else if (timerFirstStarted) {
                                    viewModel.resumeTimer()
                                } else {
                                    viewModel.startTimer(initialTime)
                                    timerFirstStarted = true
                                }
                                timerRunning = !timerRunning
                            }, isCtaButton = true)
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
                        modifier = Modifier.fillMaxWidth(if (platform == Constants.ANDROID || platform == Constants.IOS) 1f else 0.25f)
                            .align(Alignment.BottomStart),
                        Constants.musicList
                    )
                }
            }
        }
    }
}


fun formatTime(minutes: Long, seconds: Long): String {
    val formattedMinutes = minutes.toString().padStart(2, '0')
    val formattedSeconds = seconds.toString().padStart(2, '0')
    return "$formattedMinutes:$formattedSeconds"
}