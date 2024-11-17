package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.kilbourne.isaac.lysts.data.Placeholder
import ca.kilbourne.isaac.lysts.data.TodoList
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
    val selectedListIndex = remember { mutableIntStateOf(0) }
    val selectedList = todoLists[selectedListIndex.intValue]

    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet {
            MainActivityDrawer(todoLists)
        }
    }) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            MainActivityTopBar(
                toggleDrawer = toggleDrawer, selectedList = selectedList
            )
        }) {
            Box(modifier = Modifier.padding(it)) {
                MainActivityContent(selectedList)
            }
        }
    }
}