package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListItem

@Preview
@Composable
fun PreviewTodoListList() {
    TodoListList(
        lists = listOf(
            TodoList(
                "Groceries", icon = Icons.Outlined.ShoppingCart, mutableListOf(
                    TodoListItem("Item 1", false),
                    TodoListItem("Item 2", false),
                    TodoListItem("Item 3", false),
                )
            ), TodoList(
                "Movies to Watch", icon = Icons.Outlined.PlayArrow, mutableListOf(
                    TodoListItem("Item 1", false),
                    TodoListItem("Item 2", false),
                    TodoListItem("Item 3", false),
                )
            ), TodoList(
                "Places to Visit", icon = Icons.Outlined.Place, mutableListOf(
                    TodoListItem("Item 1", false),
                    TodoListItem("Item 2", false),
                    TodoListItem("Item 3", false),
                )
            )
        )
    )
}

@Composable
fun TodoListList(lists: List<TodoList>, onListSelected: (TodoList) -> Unit = {}) {
    LazyColumn {
        itemsIndexed(lists) { _, list ->
            Row(
                Modifier
                    .debouncedClickable() { onListSelected(list) }
                    .minimumInteractiveComponentSize()
                    .padding(16.dp, 0.dp)
            ) {
                Icon(list.icon, contentDescription = null)
                Spacer(Modifier.width(16.dp))
                Text(
                    text = list.name, textAlign = TextAlign.Start, modifier = Modifier.weight(1f)
                )
            }
        }
    }
}