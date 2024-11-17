package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ca.kilbourne.isaac.lysts.data.Placeholder
import ca.kilbourne.isaac.lysts.data.TodoList

@Preview
@Composable
private fun PreviewMainActivityTopBar() {
    MainActivityTopBar({}, Placeholder.TodoLists.androidFlavors())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityTopBar(
    toggleDrawer: () -> Unit = {}, selectedList: TodoList
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = toggleDrawer) {
                Icon(Icons.Outlined.Menu, contentDescription = "Menu")
            }
        },
        title = {
            Text(selectedList.name)
        },
    )
}