import `in`.mrkaydev.dhyaan.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Dhyaan") {
            App()
        }
    }
}
