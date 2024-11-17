package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.kilbourne.isaac.lysts.data.TodoListItem

@Preview
@Composable
fun PreviewTodoList() {
    Box(Modifier.width(256.dp)) {
        TodoList(
            data = mutableListOf(
                TodoListItem("Item 1", false),
                TodoListItem("Item 2", true),
                TodoListItem("Item 3", false),
                TodoListItem("Item 4", true),
                TodoListItem("Item 5", false),
                TodoListItem("Item 6", true),
            )
        )
    }
}

@Composable
fun TodoList(data: MutableList<TodoListItem>) {
    LazyColumn {
        itemsIndexed(data) { index, item ->
            ListItem(item) {
                data[index] = item.copy(done = it)
            }
        }
    }
}

@Composable
private fun ListItem(
    item: TodoListItem,
    onDoneChanged: ((newState: Boolean) -> Unit)? = null
) {
    val menuExpanded = remember { mutableStateOf(false) }
    val showDropdown: () -> Unit = { menuExpanded.value = true }
    val hideDropdown: () -> Unit = { menuExpanded.value = false }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = item.done,
            onCheckedChange = onDoneChanged,
        )
        Text(item.description, Modifier.weight(1f))
        Box {
            IconButton(onClick = showDropdown) { Text(":") }
            DropdownMenu(
                expanded = menuExpanded.value,
                onDismissRequest = hideDropdown
            ) {
                Text("Dropdown!")
            }
        }
    }
}