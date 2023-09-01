package `in`.mrkaydev.dhyaan.utils

import `in`.mrkaydev.dhyaan.ui.components.MusicPlayerData

object Utils {
    fun formatTime(minutes: Long, seconds: Long): String {
        val formattedMinutes = minutes.toString().padStart(2, '0')
        val formattedSeconds = seconds.toString().padStart(2, '0')
        return "$formattedMinutes:$formattedSeconds"
    }

    val musicList = listOf(
        MusicPlayerData("Peaceful Garden", "by Harumachi Music", "theme/ocean","sounds/audio_one.mp3","audio_one"),
        MusicPlayerData("River with faraway bird sounds", "by ValentineSeasons", "theme/tokiyo","sounds/audio_two.mp3","audio_two")
    )
}