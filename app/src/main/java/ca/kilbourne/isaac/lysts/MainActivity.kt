package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityUiRoot
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val allLists by viewModel.todoLists.getAll().collectAsStateWithLifecycle(listOf())
            val currList by viewModel.currentTodoList.withItems().collectAsStateWithLifecycle(null)

            val onItemCompletionChange: (TodoItem, Boolean) -> Unit = { item, done ->
                coroutineScope.launch {
                    viewModel.todoItems.setCompletion(item.id, done)
                }
            }

            val onAddItemRequest: () -> Unit = {
                coroutineScope.launch {
                    val listId = currList?.list?.id
                    if (listId == null) return@launch
                    viewModel.todoItems.create(TodoItem(listId = listId, description = "Item"))
                }
            }

            LystsTheme {
                MainActivityUiRoot(
                    todoLists = allLists,
                    currentList = currList,
                    onItemCompletionChange = onItemCompletionChange,
                    onAddItemRequest = onAddItemRequest
                )
            }
        }

    }
}
