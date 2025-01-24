package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.kilbourne.isaac.lysts.data.TodoList

@Preview
@Composable
fun MainActivityTopBarPreview() {
    MainActivityTopBar(TodoList(name = "Shopping List"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityTopBar(
    currentList: TodoList, showListPicker: () -> Unit = {}, showListOptions: () -> Unit = {}
) {
    CenterAlignedTopAppBar(navigationIcon = {
        IconButton(onClick = showListPicker) {
            Icon(Icons.Default.Menu, contentDescription = "Lists Menu")
        }
    }, title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.ShoppingCart,
                contentDescription = "Icon for List '${currentList.name}'"
            )

            Text(currentList.name, modifier = Modifier.padding(start = 8.dp))
        }
    }, actions = {
        IconButton(onClick = showListOptions) {
            Icon(Icons.Default.MoreVert, "More")
        }
    })
}
