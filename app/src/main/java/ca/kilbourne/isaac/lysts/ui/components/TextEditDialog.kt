package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Preview
@Composable
fun PreviewTextEditDialog() {
    TextEditDialog(onCancel = {}, onAccept = {}, text = "Text Value")
}


/**
 * A modal dialog that allows the user to edit some text.
 *
 * @param onCancel Invoked when the dialog is dismissed or the Cancel button is tapped
 * @param onAccept Invoked when the accept button is tapped, with the updated value the user entered
 * @param autoFocus When `true`, the text field automatically takes focus
 * @param text The initial value of the text input.
 * @param selection The initial selection of the text input. Defaults to the entire
 *                  initial text being selected.
 * @param cancelButtonContent The content of the Cancel button. Defaults to "Cancel"
 * @param acceptButtonContent The content of the Accept button. Defaults to "Accept"
 */
@Composable
fun TextEditDialog(
    onCancel: () -> Unit,
    onAccept: (String) -> Unit,
    autoFocus: Boolean = true,
    text: String = "",
    selection: TextRange = TextRange(0, text.length),
    acceptButtonContent: @Composable RowScope.() -> Unit = { Text("Accept") },
    cancelButtonContent: @Composable RowScope.() -> Unit = { Text("Cancel") }
) {
    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf(TextFieldValue(text = text, selection = selection)) }

    LaunchedEffect(Unit) {
        if (autoFocus) focusRequester.requestFocus()
    }

    Dialog(onDismissRequest = onCancel) {
        Card {
            Column(Modifier.padding(16.dp, 8.dp)) {
                TextField(
                    value = value,
                    onValueChange = { value = it },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                Spacer(Modifier.height(16.dp))

                Row(Modifier.align(Alignment.End)) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        content = cancelButtonContent
                    )

                    Button(
                        onClick = debounced { onAccept(value.text) },
                        content = acceptButtonContent
                    )
                }
            }
        }
    }
}
