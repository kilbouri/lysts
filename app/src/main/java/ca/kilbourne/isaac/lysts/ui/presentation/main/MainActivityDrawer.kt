package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ca.kilbourne.isaac.lysts.data.Placeholder
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.components.TodoListList

@Preview
@Composable
private fun PreviewMainActivityDrawer() {
    MainActivityDrawer(
        mutableListOf(
            Placeholder.TodoLists.androidFlavors(),
            Placeholder.TodoLists.movies(),
            Placeholder.TodoLists.places(),
            Placeholder.TodoLists.groceries(),
            Placeholder.TodoLists.empty(),
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityDrawer(todoLists: MutableList<TodoList>) {
    Column {
        TopAppBar(title = { Text("Your Lysts") }, actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Add, contentDescription = "Add List")
            }
        })
        
        TodoListList(todoLists)
    }
}
