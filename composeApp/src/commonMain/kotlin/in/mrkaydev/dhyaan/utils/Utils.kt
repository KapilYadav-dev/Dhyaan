package `in`.mrkaydev.dhyaan.utils

object Utils {
    fun formatTime(minutes: Long, seconds: Long): String {
        val formattedMinutes = minutes.toString().padStart(2, '0')
        val formattedSeconds = seconds.toString().padStart(2, '0')
        return "$formattedMinutes:$formattedSeconds"
    }
}