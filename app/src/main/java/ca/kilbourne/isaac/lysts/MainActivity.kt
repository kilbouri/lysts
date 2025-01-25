package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityTopBar
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var currentListId by remember { mutableStateOf<Long?>(null) }

            val allLists by viewModel.todoLists.getAll().collectAsStateWithLifecycle(listOf())

            LystsTheme {
                Scaffold(
                    topBar = { MainActivityTopBar(TodoList(name = "Shopping List")) },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) {
                            Icon(Icons.Default.Add, contentDescription = "Add List Item")
                        }
                    }
                ) {
                    Box(Modifier.padding(it)) {
                        if (currentListId == null) {
                            Text(
                                "List Empty",
                                Modifier
                                    .alpha(0.33f)
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center),
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
