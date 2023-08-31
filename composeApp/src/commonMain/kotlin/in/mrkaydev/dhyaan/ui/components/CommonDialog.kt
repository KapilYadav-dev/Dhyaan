package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import `in`.mrkaydev.dhyaan.theme.colorBlack
import `in`.mrkaydev.dhyaan.theme.colorWhite
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ) {

                    Image(
                        painter = painterResource("images/yoga.jpg"),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Inside
                    )
                }

                Text(
                    text = title,
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp), fontSize = 20.sp
                )

                Text(
                    text = description,
                    modifier = Modifier.padding(16.dp),
                    fontFamily = FontLoader.appFont,
                    fontWeight = FontWeight.Medium,
                )

                Row(Modifier.padding(top = 10.dp)) {
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