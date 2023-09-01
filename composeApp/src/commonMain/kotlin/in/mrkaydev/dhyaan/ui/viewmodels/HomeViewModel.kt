package `in`.mrkaydev.dhyaan.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import `in`.mrkaydev.dhyaan.AudioPlayer
import `in`.mrkaydev.dhyaan.data.HomeUiState
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.utils.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit

class HomeViewModel : ScreenModel {

    private var audioPlayer: AudioPlayer? = null
    var isAnyAudioPlaying by mutableStateOf(false)
    var pomodoroCount by mutableStateOf(0)
    var isPomodoroSelected by mutableStateOf(true)
    var isLongBreakSelected by mutableStateOf(false)
    var isShortBreakSelected by mutableStateOf(false)
    var showDialogForInstruction by mutableStateOf(false)
    var timerType by mutableStateOf("")

    private val _currentTime = MutableStateFlow((Constants.POMODORO_TIME).minutes.toLong(DurationUnit.MILLISECONDS))
    val currentTime: StateFlow<Long> = _currentTime.asStateFlow()

    private var timerJob: Job? = null
    private var remainingTime = 0L

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Nothing)
    val homeUiState = _homeUiState.asStateFlow()

    init {
        loadFonts()
        audioPlayer = AudioPlayer()
    }

    override fun onDispose() {
        super.onDispose()
        timerJob = null
        audioPlayer =null
    }

    private fun loadFonts() {
        coroutineScope.launch {
            withContext(Dispatchers.Default) { FontLoader.loadFontsAsync() }
            _homeUiState.emit(HomeUiState.Success)
        }
    }

    fun startTimer(timeInMinutes: Int) {
        timerJob = null
        tickTimer(timeInMinutes.minutes.toLong(DurationUnit.MILLISECONDS))
        when (timeInMinutes) {
            Constants.POMODORO_TIME -> {
                pomodoroCount += 1
                timerType = Constants.POMODORO_TIME_KEY
                isPomodoroSelectedActions()
            }
            Constants.BREAK_TIMER_LONG -> {
                timerType = Constants.BREAK_TIMER_LONG_KEY
                isLongBreakSelectedActions()
            }
            Constants.BREAK_TIMER_SHORT -> {
                timerType = Constants.BREAK_TIMER_SHORT_KEY
                isShortBreakSelectedActions()
            }
        }
    }

    private fun tickTimer(initialTime: Long) {
        if (timerJob == null) {
            remainingTime = initialTime
            timerJob = coroutineScope.launch {
                while (remainingTime > 0) {
                    _currentTime.value = remainingTime
                    delay(1000)
                    remainingTime -= 1000
                }
                showDialogForInstruction=true
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    fun resumeTimer() {
        if (timerJob == null) {
            tickTimer(remainingTime)
        }
    }

    fun isPomodoroSelectedActions() {
        if (!isPomodoroSelected) {
            isPomodoroSelected = !isPomodoroSelected
            isLongBreakSelected = false
            isShortBreakSelected = false
        }
    }

    fun isLongBreakSelectedActions() {
        if (!isLongBreakSelected) {
            isLongBreakSelected = !isLongBreakSelected
            isPomodoroSelected = false
            isShortBreakSelected = false
        }
    }

    fun isShortBreakSelectedActions() {
        if (!isShortBreakSelected) {
            isShortBreakSelected = !isShortBreakSelected
            isPomodoroSelected = false
            isLongBreakSelected = false
        }
    }
    fun playAudio(idx:Int) {
        val path = if (platform == Constants.ANDROID) Utils.musicList[idx].androidId else Utils.musicList[idx].audio
        path?.let { playAudio(it) }
    }

    private fun playAudio(path: String) {
        coroutineScope.launch {
            audioPlayer?.playAudio(path)
        }
    }

    fun pauseAudio() {
        audioPlayer?.pauseAudio()
    }

    fun resumeAudio() {
        audioPlayer?.resumeAudio()
    }
}