import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import `in`.mrkaydev.dhyaan.ui.screens.App
import `in`.mrkaydev.dhyaan.utils.Utils
import java.awt.GraphicsEnvironment

fun main() = application {
    val screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration.bounds.size
    Window(
        title = Utils.APP_NAME,
        state = rememberWindowState(width = screenSize.width.dp, height = screenSize.height.dp),
        onCloseRequest = ::exitApplication,
    ) {
        App()
    }
}