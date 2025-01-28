package ca.kilbourne.isaac.lysts.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.ui.theme.CustomIcons

@Composable
fun TodoListItemsColumn(listItems: List<TodoItem>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(listItems) {
            TodoListItem(item = it)
        }
    }
}

@Composable
private fun TodoListItem(
    item: TodoItem,
    onDoneChange: (Boolean) -> Unit = {},
    onNameTap: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
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
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .alpha(0.5f),
            contentDescription = "Drag handle for '${item.description}'"
        )
    }
}
