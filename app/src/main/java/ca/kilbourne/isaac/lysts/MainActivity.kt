package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListWithItems
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityUiRoot
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val allLists by viewModel.todoLists.getAll().collectAsStateWithLifecycle(listOf())
            val currList by viewModel.currentTodoList.withItems().collectAsStateWithLifecycle(null)

            // TODO: these are hardcoded until we add list management functionality
            val fakeAllLists = listOf(
                TodoList(1, "Shopping List"),
                TodoList(2, "Movie List"),
                TodoList(3, "Book List")
            )
            val fakeCurrList = TodoListWithItems(
                fakeAllLists.first(),
                listOf(
                    TodoItem(1, 1, "Apples", true),
                    TodoItem(2, 1, "Oranges", true),
                    TodoItem(3, 1, "Pears", true),
                    TodoItem(4, 1, "Potato Chips", false)
                )
            )

            LystsTheme {
                MainActivityUiRoot(fakeAllLists, fakeCurrList)
            }
        }
    }
}
