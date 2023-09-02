package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.dhyaan.theme.colorBlack
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.utils.FontLoader


@Composable
fun AlertDialog(
    positiveText: String = "ok",
    negativeText: String = "cancel",
    title: String,
    text: String,
    positiveButtonClicked: () -> Unit,
    negativeButtonClicked: () -> Unit,
    dismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            dismissRequest()
        },
        confirmButton = {
            Button(onClick = { positiveButtonClicked() }) {
                Text(
                    positiveText,
                    fontSize = 16.sp,
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Bold,
                    color = colorWhite
                )
            }
        },
        dismissButton = {
            Button(onClick = { negativeButtonClicked() }) {
                Text(
                    negativeText,
                    fontSize = 16.sp,
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Bold,
                    color = colorWhite
                )
            }
        },
        title = {
            Text(
                title,
                fontSize = 24.sp,
                fontFamily = FontLoader.appFont,
                fontWeight = FontWeight.Bold,
                color = colorBlack
            )
        },
        text = {
            Text(
                text,
                fontSize = 20.sp,
                fontFamily = FontLoader.appFont,
                fontWeight = FontWeight.Normal,
                color = colorBlack
            )
        },
    )
}