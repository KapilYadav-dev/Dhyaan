package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import `in`.mrkaydev.dhyaan.theme.colorBlack
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import org.jetbrains.compose.resources.painterResource


@Composable
fun CommonDialog(title:String,description:String,ctaButtonText:String, onDismiss: () -> Unit) {

    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(colorWhite)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Constants.imageDialogHeight),
                    ) {

                    Image(
                        painter = painterResource("images/theme/yoga.jpg"),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f),
                        contentScale = ContentScale.Inside
                    )
                    Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
                }

                Text(
                    text = title,
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp), fontSize = 20.sp
                )

                Text(
                    text = description,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Medium,
                )

                Row(Modifier.padding(top = 8.dp, start = 24.dp,end=24.dp)) {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(
                            text = ctaButtonText,
                            color = colorBlack,
                            fontFamily = FontLoader.appFont,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}