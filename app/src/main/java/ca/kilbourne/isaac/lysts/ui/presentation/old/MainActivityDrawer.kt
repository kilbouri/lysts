package ca.kilbourne.isaac.lysts.ui.presentation.old

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.components.TodoListList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityDrawer(todoLists: List<TodoList>) {
    Column {
        TopAppBar(title = { Text("Your Lysts") }, actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Add, contentDescription = "Add List")
            }
        })

        TodoListList(todoLists)
    }
}
