package ca.kilbourne.isaac.lysts.data

import androidx.compose.ui.graphics.vector.ImageVector

data class TodoList(val name: String, val icon: ImageVector, val items: MutableList<TodoListItem>)
