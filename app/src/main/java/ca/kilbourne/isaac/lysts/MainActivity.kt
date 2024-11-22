package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityPresentation
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val allLists by viewModel.allTodoLists.map { it.toMutableList() }.collectAsState(initial = mutableListOf())

            LystsTheme {
                MainActivityPresentation(todoLists = allLists)
            }
        }
    }
}