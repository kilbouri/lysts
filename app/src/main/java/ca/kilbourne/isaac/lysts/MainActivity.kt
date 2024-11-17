package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import ca.kilbourne.isaac.lysts.data.TodoListItem
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
                mutableStateListOf(
                    TodoListItem("Item 1", false),
                    TodoListItem("Item 2", true),
                    TodoListItem("Item 3", false),
                    TodoListItem("Item 4", true),
                    TodoListItem("Item 5", false),
                    TodoListItem("Item 6", true),
                    TodoListItem("Item 4", true),
                    TodoListItem("Item 5", false),
                    TodoListItem("Item 6", true),
                    TodoListItem("Item 4", true),
                    TodoListItem("Item 5", false),
                    TodoListItem("Item 6", true),
                    TodoListItem("Item 4", true),
                    TodoListItem("Item 5", false),
                    TodoListItem("Item 6", true),
                    TodoListItem("Item 7", false),
                    TodoListItem("Item 8", true)
                )
            }

            LystsTheme {
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
                                        Icon(Icons.Outlined.Menu, contentDescription = "Menu")
                                    }
                                },
                                title = {
                                    Text("Lysts")
                                },
                            )
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            TodoList(data = data)
                        }
                    }
                }
            }
        }
    }
}
