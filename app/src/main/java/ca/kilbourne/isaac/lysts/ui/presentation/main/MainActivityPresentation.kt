package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.kilbourne.isaac.lysts.data.Placeholder
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListItem
import ca.kilbourne.isaac.lysts.ui.components.TextInputDialog
import ca.kilbourne.isaac.lysts.ui.components.debounced
import kotlinx.coroutines.launch

@Preview
@Composable
private fun PreviewMainActivityPresentation() {
    MainActivityPresentation(
        mutableListOf(
            Placeholder.TodoLists.androidFlavors(),
            Placeholder.TodoLists.movies(),
            Placeholder.TodoLists.places(),
            Placeholder.TodoLists.groceries(),
            Placeholder.TodoLists.empty(),
        )
    )
}

@Composable
fun MainActivityPresentation(
    todoLists: MutableList<TodoList>
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val toggleDrawer: () -> Unit = {
        coroutineScope.launch {
            drawerState.apply { if (isClosed) open() else close() }
        }
    }

    // TODO: save and load this from storage
    val selectedListIndex by remember { mutableIntStateOf(0) }
    val selectedList = todoLists.getOrNull(selectedListIndex)

    val addItemToList: (String) -> Unit = {
        selectedList?.items?.add(TodoListItem(it, false))
    }

    var showNewItemDialog by remember { mutableStateOf(false) }
    if (showNewItemDialog) {
        TextInputDialog(onCancel = { showNewItemDialog = false }, onAccept = {
            addItemToList(it.trim())
            showNewItemDialog = false
        })
    }

    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet {
            MainActivityDrawer(todoLists)
        }
    }) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            MainActivityTopBar(
                toggleDrawer = toggleDrawer,
                onDeleteListRequest = {},
                onRenameListRequest = {},
                selectedList = selectedList ?: TodoList("", Icons.Default.PlayArrow, mutableListOf())
            )
        }, floatingActionButton = {
            FloatingActionButton(debounced { showNewItemDialog = true }) {
                Icon(Icons.Outlined.Add, contentDescription = "Add new item")
            }
        }) {
            Box(modifier = Modifier.padding(it)) {
                if (selectedList != null) {
                    MainActivityContent(selectedList)
                }
            }
        }
    }
}