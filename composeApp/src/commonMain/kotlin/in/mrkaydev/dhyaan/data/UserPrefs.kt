package `in`.mrkaydev.dhyaan.data

@kotlinx.serialization.Serializable
data class UserPrefs(
    val selectedThemeName:String="gradient-light",
    val pomodoroTimerTimeInMinutes:Int=45,
    val shortBreakTimerTimeInMinutes:Int=5,
    val longBreakTimerTimeInMinutes:Int=10,
)