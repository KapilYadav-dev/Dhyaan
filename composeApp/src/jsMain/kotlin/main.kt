import `in`.mrkaydev.dhyaan.ui.screens.App
import `in`.mrkaydev.dhyaan.utils.Utils
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow(Utils.APP_NAME) {
            App()
        }
    }
}
