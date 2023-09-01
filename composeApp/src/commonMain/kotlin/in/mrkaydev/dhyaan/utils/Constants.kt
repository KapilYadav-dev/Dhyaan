package `in`.mrkaydev.dhyaan.utils

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.dhyaan.platform
import `in`.mrkaydev.dhyaan.ui.components.MusicPlayerData

object Constants {

    const val APP_NAME: String = "Dhyaan"
    const val ANDROID: String = "android"
    const val IOS: String = "ios"
    const val WEB: String = "web"
    const val DESKTOP: String = "desktop"

    const val POMODORO_TIME: Int = 3
    const val BREAK_TIMER_LONG: Int = 2
    const val BREAK_TIMER_SHORT: Int = 1
    const val POMODORO_SERIES_FREQUENCY: Int = 4

    const val RECOMPOSER_ENABLED: Boolean = true

    const val wallPaperId: String = "tokiyo"
    const val POMODORO_TIME_KEY: String = "POMODORO_TIME"
    const val BREAK_TIMER_LONG_KEY: String = "BREAK_TIMER_LONG"
    const val BREAK_TIMER_SHORT_KEY: String = "BREAK_TIMER_SHORT"

    const val breakDescription: String = "Congratulations, you've completed your $POMODORO_TIME minutes of mindfulness meditation on the app. Now, it's time to take a well-deserved break. Enjoy!"
    const val focusDescription: String = "Let's refocus and get back to work again..."


    val imageDialogHeight = if (platform == WEB || platform == DESKTOP) 240.dp else 160.dp
    val buttonWidth = if (platform == WEB || platform == DESKTOP) 160.dp else 120.dp
    val verticalSpacer = if (platform == WEB || platform == DESKTOP) 56.dp else 24.dp
    val settingSize = if (platform == WEB || platform == DESKTOP) 24.dp else 20.dp


    val settingHeaderText = if (platform == WEB || platform == DESKTOP) 40.sp else 24.sp
    val settingOptionTitleText = if (platform == WEB || platform == DESKTOP) 24.sp else 16.sp
    val headerTitleTextSize = if (platform == WEB || platform == DESKTOP) 32.sp else 20.sp
    val headerDevTitleTextSize = if (platform == WEB || platform == DESKTOP) 18.sp else 14.sp
    val buttonTextSize = if (platform == WEB || platform == DESKTOP) 20.sp else 16.sp
    val timerTextSize = if (platform == WEB || platform == DESKTOP) 120.sp else 48.sp
}