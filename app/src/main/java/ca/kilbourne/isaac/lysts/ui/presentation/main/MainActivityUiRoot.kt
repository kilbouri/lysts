package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.sp
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListWithItems

@Composable
fun MainActivityUiRoot(todoLists: List<TodoList>, currentList: TodoListWithItems?) {
    Scaffold(
        topBar = { MainActivityTopBar(currentList?.list) },
        floatingActionButton = {
            if (currentList != null) {
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Default.Add, contentDescription = "Add List Item")
                }
            }
        }
    ) {
        Box(Modifier.padding(it)) {
            if (currentList == null) HintText("No List Selected")
            else if (currentList.items.isEmpty()) HintText("List Empty")
        }
    }
}

@Composable
private fun HintText(text: String) {
    Text(
        text, Modifier
            .alpha(0.33f)
            .fillMaxSize()
            .wrapContentSize(), fontSize = 20.sp
    )
}
