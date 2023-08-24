package `in`.mrkaydev.dhyaan

import kotlinx.browser.window

internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}