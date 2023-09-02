package `in`.mrkaydev.dhyaan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
 * This is used to prevent un-necessary recomposition to parent column
 */
@Composable
fun ColumnComposed(modifier: Modifier,content: @Composable () -> Unit) {
    Column(
        modifier = modifier
    ) {
        content()
    }
}