import androidx.compose.ui.window.ComposeUIViewController
import `in`.mrkaydev.dhyaan.ui.screens.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
