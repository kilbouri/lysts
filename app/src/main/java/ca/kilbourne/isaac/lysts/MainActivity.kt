package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.ui.components.TodoList
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            val toggleDrawer: () -> Unit = {
                scope.launch {
                    drawerState.apply { if (isClosed) open() else close() }
                }
            }

            val data = remember {
                mutableStateListOf<TodoItem>(
                    TodoItem("Item 1", false),
                    TodoItem("Item 2", true),
                    TodoItem("Item 3", false),
                    TodoItem("Item 4", true),
                    TodoItem("Item 5", false),
                    TodoItem("Item 6", true),
                    TodoItem("Item 7", false),
                    TodoItem("Item 8", true)
                )
            }

            LystsTheme(darkTheme = true) {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = true,
                    drawerContent = {
                        ModalDrawerSheet {
                            Text("Drawer")
                        }
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                navigationIcon = {
                                    IconButton(onClick = toggleDrawer) {
                                        Text("=")
                                    }
                                },
                                title = {
                                    Text("Lysts")
                                },
                            )
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            TodoList(items = data)
                        }
                    }
                }
            }
        }
    }
}
