package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityPresentation
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val allLists by viewModel.allTodoLists.map { it.toMutableList() }
                .collectAsStateWithLifecycle(initialValue = mutableListOf())

            LystsTheme {
                MainActivityPresentation(
                    todoLists = allLists,
                    allLists.firstOrNull() ?: TodoList(
                        "..",
                        Icons.Outlined.PlayArrow,
                        mutableListOf()
                    )
                )
            }
        }
    }
}t