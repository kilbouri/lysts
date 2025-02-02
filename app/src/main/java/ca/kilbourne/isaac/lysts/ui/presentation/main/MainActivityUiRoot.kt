package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.sp
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListWithItems
import ca.kilbourne.isaac.lysts.ui.components.TodoListItemsColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityUiRoot(
    todoLists: List<TodoList>,
    currentList: TodoListWithItems?,
    onItemCompletionChange: (TodoItem, Boolean) -> Unit = { _, _ -> },
    onAddItemRequest: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MainActivityTopBar(
                currentList = currentList?.list,
                showListPicker = { showBottomSheet = true }
            )
        },
        floatingActionButton = {
            if (currentList != null) {
                FloatingActionButton(onClick = onAddItemRequest) {
                    Icon(Icons.Default.Add, contentDescription = "Add List Item")
                }
            }
        }
    ) { innerPadding ->
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = bottomSheetState
            ) {
                Button(onClick = {
                    coroutineScope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                        showBottomSheet = false
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }
        Box(modifier = Modifier.padding(innerPadding)) {
            if (currentList == null) HintText("No List Selected")
            else if (currentList.items.isEmpty()) HintText("List Empty")
            else TodoListItemsColumn(currentList.items, onItemCompletionChange)
        }
    }
}

@Composable
private fun HintText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .alpha(0.33f)
            .fillMaxSize()
            .wrapContentSize(), fontSize = 20.sp
    )
}
