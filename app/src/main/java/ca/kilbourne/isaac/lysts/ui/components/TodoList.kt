package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
    val itemToRename = remember { mutableStateOf<Int?>(null) }
    val showRenameFor = { idx: Int -> itemToRename.value = idx }
    val closeRename = { itemToRename.value = null }

    if (itemToRename.value != null) {
        val index = itemToRename.value!!

        RenameDialog(
            defaultValue = data[index].description,
            onCancelRename = closeRename,
            onAcceptRename = {
                data[index] = data[index].copy(description = it)
                closeRename()
            }
        )
    }

    LazyColumn {
        itemsIndexed(data) { index, item ->
            ListItem(
                item = item,
                onDoneChanged = { data[index] = item.copy(done = it) },
                onDeleteRequest = debounced { data.removeAt(index) },
                onRenameRequest = debounced { showRenameFor(index) }
            )
        }
    }
}

@Composable
private fun ListItem(
    item: TodoListItem,
    onDoneChanged: ((newState: Boolean) -> Unit)? = null,
    onDeleteRequest: (() -> Unit) = {},
    onRenameRequest: (() -> Unit) = {}
) {
    val menuExpanded = remember { mutableStateOf(false) }
    val showDropdown: () -> Unit = { menuExpanded.value = true }
    val hideDropdown: () -> Unit = { menuExpanded.value = false }

    val closeDropdownThen: (func: () -> Unit) -> () -> Unit = {
        { hideDropdown(); it() }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = item.done,
            onCheckedChange = onDoneChanged,
        )
        Text(item.description, Modifier.weight(1f))
        Box {
            IconButton(onClick = showDropdown) {
                Icon(
                    Icons.Outlined.MoreVert,
                    contentDescription = "More Options"
                )
            }
            DropdownMenu(
                expanded = menuExpanded.value,
                onDismissRequest = hideDropdown,
            ) {
                DropdownMenuItem(
                    text = { Text("Rename") },
                    onClick = closeDropdownThen(onRenameRequest),
                    leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) }
                )
                DropdownMenuItem(
                    text = { Text("Delete") },
                    onClick = closeDropdownThen(onDeleteRequest),
                    leadingIcon = { Icon(Icons.Outlined.Delete, contentDescription = null) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRenameDialog() {
    RenameDialog("Name", {}, {})
}

@Composable
private fun RenameDialog(
    defaultValue: String = "",
    onCancelRename: () -> Unit,
    onAcceptRename: (String) -> Unit
) {
    val newName = remember { mutableStateOf(defaultValue) }
    Dialog(onDismissRequest = onCancelRename) {
        Card {
            Column {
                TextField(value = newName.value, onValueChange = { newName.value = it })
                Row(Modifier.align(Alignment.End)) {
                    Button(onClick = onCancelRename) {
                        Text("Cancel")
                    }
                    Button(onClick = debounced { onAcceptRename(newName.value) }) {
                        Text("Rename")
                    }
                }
            }
        }
    }
}