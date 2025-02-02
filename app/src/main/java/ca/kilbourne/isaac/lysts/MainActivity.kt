package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityUiRoot
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val scope = rememberCoroutineScope()
            val allLists by viewModel.todoLists.getAll().collectAsStateWithLifecycle(listOf())
            val currList by viewModel.todoLists.currentWithItems().collectAsStateWithLifecycle(null)

            LystsTheme {
                MainActivityUiRoot(
                    todoLists = allLists,
                    currentList = currList,
                    onItemCompletionChange = viewModel.onItemCompletionChange(scope),
                    onAddItemRequest = viewModel.onAddItemRequest(currList?.list?.id, scope),
                    onRemoveDoneRequest = viewModel.onRemoveDoneRequest(currList?.list?.id, scope),
                    onClearListRequest = viewModel.onRemoveAllRequest(currList?.list?.id, scope)
                )
            }
        }
    }
}
