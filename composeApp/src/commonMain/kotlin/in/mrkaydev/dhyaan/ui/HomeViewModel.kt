package `in`.mrkaydev.dhyaan.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import `in`.mrkaydev.dhyaan.data.HomeUiState
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit

class HomeViewModel : ScreenModel {

    var isPomodoroSelected by mutableStateOf(true)
    var isLongBreakSelected by mutableStateOf(false)
    var isShortBreakSelected by mutableStateOf(false)

    private val _currentTime = MutableStateFlow((Constants.POMODORO_TIME).minutes.toLong(DurationUnit.MILLISECONDS))
    val currentTime: StateFlow<Long> = _currentTime.asStateFlow()

    private var timerJob: Job? = null
    private var remainingTime = 0L

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Nothing)
    val homeUiState = _homeUiState.asStateFlow()

    init {
        loadFonts()
    }

    private fun loadFonts() {
        coroutineScope.launch {
            withContext(Dispatchers.Default) { FontLoader.loadFontsAsync() }
            _homeUiState.emit(HomeUiState.Success)
        }
    }

    fun startTimer(initialTime: Long) {
        if (timerJob == null) {
            remainingTime = initialTime
            timerJob = coroutineScope.launch {
                while (remainingTime > 0) {
                    _currentTime.value = remainingTime
                    delay(1000)
                    remainingTime -= 1000
                }
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    fun resumeTimer() {
        if (timerJob == null) {
            startTimer(remainingTime)
        }
    }
}