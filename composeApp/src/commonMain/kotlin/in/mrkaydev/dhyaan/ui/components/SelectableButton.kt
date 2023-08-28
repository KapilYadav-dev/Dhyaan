package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import `in`.mrkaydev.dhyaan.theme.colorBlack
import `in`.mrkaydev.dhyaan.theme.colorTransparent
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.utils.Utils.buttonWidth
import `in`.mrkaydev.dhyaan.utils.Utils.buttonTextSize

@Composable
fun SelectableButton(text: String, isClicked: () -> Boolean, buttonClicked: () -> Unit) {
    val bgColor by animateColorAsState(if (isClicked()) colorWhite else colorTransparent)
    val textColor by animateColorAsState(if (isClicked()) colorBlack else colorWhite)


    Box(
        modifier = Modifier.defaultMinSize(buttonWidth).clip(RoundedCornerShape(32.dp))
            .background(bgColor)
            .border(2.dp, colorWhite, RoundedCornerShape(32.dp))
            .clickable {
                buttonClicked()
            }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.Center),
            style = TextStyle(
                fontSize = buttonTextSize,
                fontFamily = FontLoader.appFont,
                fontWeight = FontWeight.Bold,
                color = textColor,
            ),
            textAlign = TextAlign.Center
        )
    }
}