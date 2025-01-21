package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ca.kilbourne.isaac.lysts.data.Placeholder
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.components.TodoList

@Preview
@Composable
private fun PreviewMainActivityContent() {
    MainActivityContent(Placeholder.TodoLists.androidFlavors())
}

@Composable
fun MainActivityContent(todoList: TodoList) {
    TodoList(data = /*todoList.items*/ mutableListOf())
}