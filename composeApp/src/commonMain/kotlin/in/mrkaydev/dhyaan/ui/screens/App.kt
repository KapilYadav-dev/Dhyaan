package `in`.mrkaydev.dhyaan.ui.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator

@Composable
internal fun App() {
    Navigator(Home(), onBackPressed =null)
}