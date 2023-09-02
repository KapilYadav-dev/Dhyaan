package `in`.mrkaydev.dhyaan.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import compose.icons.FeatherIcons
import compose.icons.feathericons.Monitor
import compose.icons.feathericons.RefreshCcw
import `in`.mrkaydev.dhyaan.data.HomeUiState
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.showFullScreenWebOnly
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.ui.components.*
import `in`.mrkaydev.dhyaan.ui.viewmodels.HomeViewModel
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.utils.Utils
import `in`.mrkaydev.dhyaan.utils.Utils.formatTime
import `in`.mrkaydev.dhyaan.utils.recomposeHighlighter
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource


class Home : Screen {
    override val key: ScreenKey
        get() = "homeScreen"

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { HomeViewModel() }
        val homeUiState = viewModel.homeUiState.collectAsState()
        val currentTime by viewModel.currentTime.collectAsState()

        var isSettingOpened by remember { mutableStateOf(false) }
        var timerFirstStarted by remember { mutableStateOf(false) }
        var timerRunning by remember { mutableStateOf(false) }
        val rotationState = remember { Animatable(0f) }
        val scope = rememberCoroutineScope()

        when (homeUiState.value) {
            HomeUiState.Loading -> {

            }
            HomeUiState.Nothing -> {

            }
            HomeUiState.Success -> {
                val seconds = currentTime / 1000
                val minutes = seconds / 60
                val formattedTime = formatTime(minutes, seconds % 60)

                Box(Modifier.recomposeHighlighter().fillMaxSize()) {
                    Image(
                        painterResource(
                            "images/theme/${
                                Utils.settings.getString(
                                    Constants.SELECTED_THEME_KEY,
                                    Constants.wallPaperId
                                )
                            }.jpeg"
                        ),
                        "bg",
                        Modifier.recomposeHighlighter().fillMaxSize(),
                        contentScale = if (platform == Constants.ANDROID) ContentScale.Crop else ContentScale.FillBounds
                    )
                    ColumnComposed(
                        Modifier.recomposeHighlighter().padding(start = 24.dp, top = 48.dp)
                    ) {
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
                    ColumnComposed(Modifier.align(Alignment.TopEnd)) {
                        Image(
                            painterResource("images/ui/setting.png"),
                            "",
                            Modifier.recomposeHighlighter()
                                .padding(vertical = 64.dp, horizontal = 24.dp)
                                .size(Constants.settingSize)
                                .align(Alignment.TopEnd)
                                .clickable {
                                    isSettingOpened = true
                                }
                        )
                    }
                    ColumnComposed(
                        Modifier.recomposeHighlighter().align(Alignment.Center).fillMaxWidth()
                    ) {
                        RowComposed(
                            Modifier.recomposeHighlighter().fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                        ) {
                            Spacer(Modifier.recomposeHighlighter().width(32.dp))
                            SelectableButton(
                                text = { "pomodoro" },
                                isClicked = { viewModel.isPomodoroSelected }
                            ) {
                                viewModel.isPomodoroSelectedActions()
                            }

                            Spacer(Modifier.recomposeHighlighter().width(16.dp))
                            SelectableButton(
                                text = { "short break" },
                                isClicked = { viewModel.isShortBreakSelected }
                            ) {
                                viewModel.isShortBreakSelectedActions()
                            }

                            Spacer(Modifier.recomposeHighlighter().width(16.dp))
                            SelectableButton(
                                text = { "long break" },
                                isClicked = { viewModel.isLongBreakSelected }
                            ) {
                                viewModel.isLongBreakSelectedActions()
                            }
                            Spacer(Modifier.recomposeHighlighter().width(32.dp))
                        }
                        Spacer(Modifier.recomposeHighlighter().height(Constants.verticalSpacer))
                        Text(
                            formattedTime,
                            fontSize = Constants.timerTextSize,
                            fontFamily = FontLoader.appFont,
                            fontWeight = FontWeight.Bold,
                            color = colorWhite,
                            modifier = Modifier.recomposeHighlighter().fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.recomposeHighlighter().height(Constants.verticalSpacer))
                        RowComposed(
                            Modifier.recomposeHighlighter().fillMaxWidth(),
                        ) {
                            SelectableButton(text = if (timerRunning) {
                                { "Pause" }
                            } else {
                                { "Start" }
                            }, isClicked = { !timerRunning }, buttonClicked = {
                                if (timerRunning) {
                                    viewModel.pauseTimer()
                                } else if (timerFirstStarted) {
                                    viewModel.resumeTimer()
                                } else {
                                    viewModel.startTimer(Constants.POMODORO_TIME)
                                    timerFirstStarted = true
                                }
                                timerRunning = !timerRunning
                            }, isCtaButton = { true }
                            )
                            Spacer(modifier = Modifier.recomposeHighlighter().width(32.dp))
                            Icon(
                                FeatherIcons.RefreshCcw,
                                "",
                                tint = colorWhite,
                                modifier = Modifier.recomposeHighlighter().size(32.dp).clickable {
                                    scope.launch {
                                        rotationState.animateTo(
                                            targetValue = rotationState.targetValue + 360f,
                                            animationSpec = tween(durationMillis = 2000)
                                        )
                                    }
                                }.graphicsLayer(rotationZ = rotationState.value)
                            )
                        }
                    }
                    AnimatedVisibility(
                        Utils.settings.getBoolean(
                            Constants.MUSIC_ENABLED_KEY,
                            true
                        ),
                        modifier = Modifier.recomposeHighlighter()
                            .fillMaxWidth(if (platform == Constants.ANDROID || platform == Constants.IOS) 1f else 0.25f)
                            .align(Alignment.BottomStart)
                    ) {
                        ColumnComposed(Modifier) {
                            MusicPlayer(
                                modifier = Modifier.recomposeHighlighter()
                                    .fillMaxWidth(),
                                { viewModel }
                            )
                        }
                    }
                    if(!Utils.settings.getBoolean(
                            Constants.MUSIC_ENABLED_KEY,
                            true
                        )) {
                        viewModel.pauseAudio()
                    }
                    if (platform == Constants.WEB) Icon(
                        FeatherIcons.Monitor,
                        "",
                        modifier = Modifier.recomposeHighlighter().padding(32.dp).size(32.dp)
                            .align(Alignment.BottomEnd)
                            .clickable {
                                showFullScreenWebOnly()
                            },
                        tint = colorWhite
                    )
                }

                AnimatedVisibility(viewModel.showDialogForInstruction) {
                    val data = viewModel.timerType
                    val title = if (data == Constants.POMODORO_TIME_KEY) {
                        "Break time"
                    } else {
                        "Let's focus"
                    }
                    val description = if (data == Constants.POMODORO_TIME_KEY) {
                        Constants.breakDescription
                    } else {
                        Constants.focusDescription
                    }
                    CommonDialog(
                        title = title,
                        description = description,
                        "sure, make sense"
                    ) {
                        when (data) {
                            Constants.POMODORO_TIME_KEY -> {
                                if (viewModel.pomodoroCount > Constants.POMODORO_SERIES_FREQUENCY) viewModel.startTimer(
                                    Constants.BREAK_TIMER_LONG
                                ) else viewModel.startTimer(Constants.BREAK_TIMER_SHORT)
                            }
                            Constants.BREAK_TIMER_LONG_KEY -> {
                                viewModel.startTimer(Constants.POMODORO_TIME)
                            }
                            Constants.BREAK_TIMER_SHORT_KEY -> {
                                viewModel.startTimer(Constants.POMODORO_TIME)
                            }
                        }
                        viewModel.showDialogForInstruction = false
                    }
                }
                AnimatedVisibility(isSettingOpened) {
                    SettingDialog {
                        isSettingOpened = false
                    }
                }
            }
        }
    }
}