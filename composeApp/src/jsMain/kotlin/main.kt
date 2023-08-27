import `in`.mrkaydev.dhyaan.ui.screens.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Dhyaan") {
            App()
        }
    }
}
