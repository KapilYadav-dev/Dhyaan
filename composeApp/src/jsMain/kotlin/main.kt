import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import `in`.mrkaydev.dhyaan.ui.screens.App
import `in`.mrkaydev.dhyaan.utils.Constants
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow(Constants.APP_NAME,"ComposeTarget"){
            App()
        }
    }
}
