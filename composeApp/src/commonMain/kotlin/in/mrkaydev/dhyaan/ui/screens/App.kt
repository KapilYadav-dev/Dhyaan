package `in`.mrkaydev.dhyaan.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import `in`.mrkaydev.dhyaan.ui.components.CommonDialog

@Composable
internal fun App() {
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

    Navigator(Home(), onBackPressed = {
        isBackPressed = it.key == "homeScreen"
        false
    })
}