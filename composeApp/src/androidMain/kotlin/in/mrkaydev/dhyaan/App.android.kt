package `in`.mrkaydev.dhyaan

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import `in`.mrkaydev.dhyaan.ui.components.CommonDialog
import `in`.mrkaydev.dhyaan.ui.screens.App

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            var isBackPressed by rememberSaveable { mutableStateOf(false) }

            if (isBackPressed) CommonDialog(positiveButtonClicked = {
                isBackPressed = false
            }, negativeButtonClicked = {
                isBackPressed = false
            }, dismissRequest = {
                isBackPressed = false
            },
                title = "do you want to exit ?",
                text = "if you want to exit, click exit"
            )
            BackHandler {
                isBackPressed=true
            }
            App()
        }
    }
}