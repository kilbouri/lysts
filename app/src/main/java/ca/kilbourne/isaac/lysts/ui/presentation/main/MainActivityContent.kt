package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.runtime.Composable
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.components.TodoList

@Composable
fun MainActivityContent(todoList: TodoList) {
    TodoList(data = /*todoList.items*/ mutableListOf())
}
