package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.utils.recomposeHighlighter

@Composable
fun ToggleText(modifier: Modifier,toggleText:()->String,enabled:()->Boolean,onToggleChanged: (Boolean) -> Unit) {
    var isToggled by remember { mutableStateOf(enabled()) }
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Switch(
            checked = isToggled,
            onCheckedChange = { newValue ->
                isToggled = newValue
                onToggleChanged(newValue)
            }
        )
        Text(
            toggleText(),
            fontSize = 16.sp,
            color = colorWhite,
            modifier = Modifier.recomposeHighlighter().padding(start = 16.dp),
            fontFamily = FontLoader.appFont,
            fontWeight = FontWeight.Normal,
        )
    }
}