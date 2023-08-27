package `in`.mrkaydev.dhyaan

import android.content.Intent
import android.net.Uri

val context = AndroidApp.INSTANCE

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}

internal actual val platform: String
    get() = "android"