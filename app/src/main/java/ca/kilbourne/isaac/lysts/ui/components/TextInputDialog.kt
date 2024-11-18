package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
private fun PreviewTextInputDialog() {
    TextInputDialog("Text Value", onCancel = {}, onAccept = {})
}

@Composable
fun TextInputDialog(
    initialValue: String = "",
    cancelButtonLabel: String = "Cancel",
    acceptButtonLabel: String = "Accept",
    autoFocus: Boolean = true,
    onCancel: () -> Unit,
    onAccept: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var value: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = initialValue, selection = TextRange(initialValue.length)
            )
        )
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
                        onClick = onCancel, colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text(cancelButtonLabel)
                    }

                    Button(onClick = debounced { onAccept(value.text) }) {
                        Text(acceptButtonLabel)
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        if (autoFocus) focusRequester.requestFocus()
    }
}