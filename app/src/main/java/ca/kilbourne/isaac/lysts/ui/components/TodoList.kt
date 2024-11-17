package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.kilbourne.isaac.lysts.data.TodoItem

@Preview
@Composable
fun PreviewTodoList() {
    TodoList(
        items = mutableListOf(
            TodoItem("Item 1", false),
            TodoItem("Item 2", true),
            TodoItem("Item 3", false),
            TodoItem("Item 4", true),
            TodoItem("Item 5", false),
            TodoItem("Item 6", true),
            TodoItem("Item 7", false),
            TodoItem("Item 8", true)
        )
    )
}

@Composable
fun TodoList(items: MutableList<TodoItem>) {
    Column(
        Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Red)) {
        items.mapIndexed { index, todoItem ->
            ListItem(todoItem.description, todoItem.done) {
                items[index] = todoItem.copy(done = it)
            }
        }
    }
}

@Composable
private fun ListItem(label: String, done: Boolean, onDoneChanged: (newState: Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Yellow)
    ) {
        Checkbox(
            checked = done,
            onCheckedChange = onDoneChanged,
        )
        Text(label)
        Spacer(Modifier.weight(1f))
        IconButton(onClick = {}) { Text(":") }
    }
}