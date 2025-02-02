package ca.kilbourne.isaac.lysts.ui.presentation.old

import androidx.compose.runtime.Composable
import ca.kilbourne.isaac.lysts.data.domain.TodoItem
import ca.kilbourne.isaac.lysts.ui.components.TodoList

@Composable
fun MainActivityContent(todoList: MutableList<TodoItem>) {
    // TODO: this should not use a mutable list!
    TodoList(data = todoList)
}
