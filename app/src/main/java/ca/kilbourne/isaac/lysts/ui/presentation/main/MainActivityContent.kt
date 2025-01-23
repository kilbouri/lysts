package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.runtime.Composable
import ca.kilbourne.isaac.lysts.data.TodoListItem
import ca.kilbourne.isaac.lysts.ui.components.TodoList

@Composable
fun MainActivityContent(todoList: MutableList<TodoListItem>) {
    // TODO: this should not use a mutable list!
    TodoList(data = todoList)
}
