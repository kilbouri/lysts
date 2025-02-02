package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.ui.theme.CustomIcons

@Composable
fun TodoListItemsColumn(
    listItems: List<TodoItem>,
    onItemCompletionChange: (TodoItem, Boolean) -> Unit = { _, _ -> }
) {
    LazyColumn {
        items(listItems, key = { item -> item.id }) {
            TodoListItem(item = it, onDoneChange = { done -> onItemCompletionChange(it, done) })
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun TodoListItem(
    item: TodoItem,
    onDoneChange: (Boolean) -> Unit = {},
    onNameTap: () -> Unit = {},
) {
    TwoWayAnchoredDraggableBox(
        offsetSize = 64.dp,
        leftContent = { TodoItemDeleteArea(it) },
        rightContent = { TodoItemDeleteArea(it) }
    ) { offset ->
        Row(verticalAlignment = Alignment.CenterVertically, modifier = offset) {
            Checkbox(checked = item.done, onCheckedChange = onDoneChange)
            Text(
                item.description,
                Modifier
                    .clickable(onClick = onNameTap)
                    .minimumInteractiveComponentSize()
            )

            Spacer(Modifier.weight(1f))

            Icon(
                CustomIcons.DragIndicator,
                contentDescription = "Drag handle for '${item.description}'",
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .alpha(0.5f)
            )
        }
    }
}

@Composable
private fun TodoItemDeleteArea(modifier: Modifier, onDeleteRequest: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(MaterialTheme.colorScheme.errorContainer)
            .clickable(onClick = onDeleteRequest)
            .minimumInteractiveComponentSize()
            .fillMaxSize()
    ) {
        Icon(
            Icons.Outlined.Delete,
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.onErrorContainer,
        )
    }
}
