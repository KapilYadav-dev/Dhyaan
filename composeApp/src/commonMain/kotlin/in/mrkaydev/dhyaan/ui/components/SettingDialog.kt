package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import `in`.mrkaydev.dhyaan.theme.colorBlack
import `in`.mrkaydev.dhyaan.theme.colorWhite
import `in`.mrkaydev.dhyaan.ui.screens.*
import `in`.mrkaydev.dhyaan.utils.Constants
import `in`.mrkaydev.dhyaan.utils.FontLoader
import `in`.mrkaydev.dhyaan.utils.Utils.settingsItem
import `in`.mrkaydev.dhyaan.utils.recomposeHighlighter
import org.jetbrains.compose.resources.painterResource


@Composable
fun SettingDialog(onDismiss: () -> Unit) {

    var selectSettingTitleIdx by remember { mutableStateOf(0) }
    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.recomposeHighlighter()
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {
            Column(modifier = Modifier.recomposeHighlighter().fillMaxWidth().background(colorBlack)) {
                Row(modifier = Modifier.recomposeHighlighter().fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        "settings",
                        fontSize = Constants.settingHeaderText,
                        color = colorWhite,
                        modifier = Modifier.recomposeHighlighter().padding(24.dp),
                        fontFamily = FontLoader.appFont,
                        fontWeight = FontWeight.Bold
                    )
                    Image(painter = painterResource("images/ui/close.png"),"",  modifier = Modifier.recomposeHighlighter().padding(32.dp).size(24.dp).clickable {
                        onDismiss()
                    })
                }
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.recomposeHighlighter().fillMaxWidth(),
                    color = colorWhite.copy(0.4f)
                )
                Row(Modifier.recomposeHighlighter().fillMaxWidth().fillMaxHeight(0.5f)) {
                    LazyColumn(Modifier.recomposeHighlighter().weight(0.34f)) {
                        itemsIndexed(settingsItem) { idx, item ->
                            Column(modifier = Modifier.recomposeHighlighter().clickable {
                                selectSettingTitleIdx = idx
                            }) {
                                Text(
                                    item,
                                    fontSize = Constants.settingOptionTitleText,
                                    color = colorWhite,
                                    modifier = Modifier.recomposeHighlighter().padding(
                                        start = 24.dp,
                                        top = 16.dp,
                                        bottom = 16.dp
                                    ),
                                    fontFamily = FontLoader.appFont,
                                    fontWeight = FontWeight.Medium
                                )
                                if (selectSettingTitleIdx == idx) Divider(
                                    thickness = 2.dp,
                                    modifier = Modifier.recomposeHighlighter().fillMaxWidth().padding(horizontal = 16.dp),
                                    color = colorWhite
                                )
                            }
                        }
                    }
                    Divider(
                        thickness = 1.dp,
                        modifier = Modifier.recomposeHighlighter().fillMaxHeight().weight(0.002f),
                        color = colorWhite.copy(0.4f)
                    )
                    Column(Modifier.recomposeHighlighter().weight(0.65f)) {
                        when (settingsItem[selectSettingTitleIdx]) {
                            "general" -> {
                                GeneralSettings()
                            }
                            "timer" -> {
                                TimerSettings()
                            }
                            "sounds" -> {
                                SoundSettings()
                            }
                            "account" -> {
                                AccountSettings()
                            }
                        }
                    }
                    Spacer(Modifier.recomposeHighlighter().height(16.dp))
                }
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.recomposeHighlighter().fillMaxWidth(),
                    color = colorWhite.copy(0.4f)
                )
                Row(
                    Modifier.recomposeHighlighter().fillMaxWidth().padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SelectableButton({"close"}, { true }, { true }) {
                        onDismiss()
                    }
                }
            }
        }
    }
}