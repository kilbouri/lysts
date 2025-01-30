package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

            LystsTheme {
                MainActivityUiRoot(allLists, currList)
            }
        }
    }
}
