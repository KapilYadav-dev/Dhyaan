package `in`.mrkaydev.dhyaan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.ui.components.DropdownPickerInt
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.utils.Utils
import kotlinx.coroutines.launch

@Composable
fun TimerSettings() {
    var pomodoroTimer by remember { mutableStateOf(Utils.settings.getInt("pomodoroTimer",Constants.POMODORO_TIME)) }
    var shortTimer by remember { mutableStateOf(Utils.settings.getInt("shortTimer",Constants.BREAK_TIMER_SHORT)) }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "select pomodoro timer",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontLoader.appFont,
                fontWeight = FontWeight.Bold,
                color = colorWhite
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        )
        DropdownPickerInt(
            Utils.pomodoroList,
            "$pomodoroTimer minutes"
        ) {
            scope.launch {
                Utils.settings.putInt("pomodoroTimer", it)
                pomodoroTimer = it
            }
        }
        Text(
            text = "select short break timer",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontLoader.appFont,
                fontWeight = FontWeight.Bold,
                color = colorWhite
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
        )
        DropdownPickerInt(
            Utils.shortTimerList,
            "$shortTimer minutes"
        ) {
            scope.launch {
                Utils.settings.putInt("shortTimer", it)
                shortTimer = it
            }
        }
    }
}