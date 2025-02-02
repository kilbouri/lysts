package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.kilbourne.isaac.lysts.data.domain.TodoItem

@Preview
@Composable
fun PreviewTodoList() {
    Box(Modifier.width(256.dp)) {
        TodoList(
//            data = Placeholder.TodoLists.places().items
            data = mutableListOf()
        )
    }
}

@Composable
fun TodoList(data: MutableList<TodoItem>) {
    var itemToRename by remember { mutableStateOf<Int?>(null) }
    val showRenameFor = { idx: Int -> itemToRename = idx }
    val closeRename = { itemToRename = null }

    if (itemToRename != null) {
        val index = itemToRename!!

        TextInputDialog(initialValue = data[index].description,
                        acceptButtonLabel = "Rename",
                        onCancel = closeRename,
                        onAccept = {
                            data[index] = data[index].copy(description = it)
                            closeRename()
                        })
    }

    LazyColumn {
        itemsIndexed(data) { index, item ->
            ListItem(item = item,
                     onDoneChanged = { data[index] = item.copy(done = it) },
                     onDeleteRequest = debounced { data.removeAt(index) },
                     onRenameRequest = debounced { showRenameFor(index) })
        }
    }
}

@Composable
private fun ListItem(
    item: TodoItem,
    onDoneChanged: ((newState: Boolean) -> Unit)? = null,
    onDeleteRequest: (() -> Unit) = {},
    onRenameRequest: (() -> Unit) = {}
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val closeDropdownThen: (func: () -> Unit) -> () -> Unit = {
        { menuExpanded = false; it() }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = item.done,
            onCheckedChange = onDoneChanged,
        )
        Text(item.description, Modifier.weight(1f))
        Box {
            IconButton(onClick = { menuExpanded = true }) {
                Icon(
                    Icons.Outlined.MoreVert, contentDescription = "More Options"
                )
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },
            ) {
                DropdownMenuItem(text = { Text("Rename") },
                                 onClick = closeDropdownThen(onRenameRequest),
                                 leadingIcon = {
                                     Icon(
                                         Icons.Outlined.Edit,
                                         contentDescription = null
                                     )
                                 })
                DropdownMenuItem(text = { Text("Delete") },
                                 onClick = closeDropdownThen(onDeleteRequest),
                                 colors = MenuDefaults.itemColors(MaterialTheme.colorScheme.error),
                                 leadingIcon = {
                                     Icon(
                                         Icons.Outlined.Delete,
                                         tint = MaterialTheme.colorScheme.error,
                                         contentDescription = null
                                     )
                                 })
            }
        }
    }
}
