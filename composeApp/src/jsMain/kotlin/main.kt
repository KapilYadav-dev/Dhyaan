import `in`.mrkaydev.dhyaan.ui.screens.App
import `in`.mrkaydev.dhyaan.utils.Constants
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow(Constants.APP_NAME) {
            App()
        }
    }
}
