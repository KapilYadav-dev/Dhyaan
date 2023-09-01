package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/*
 * This is used to prevent un-necessary recomposition to parent column
 */
@Composable
fun RowComposed(
    modifier: Modifier,
    horizontalArrangement: Arrangement.HorizontalOrVertical=Arrangement.Center,
    verticalAlignment: Alignment.Vertical=Alignment.CenterVertically,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        content()
    }
}