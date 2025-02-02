package ca.kilbourne.isaac.lysts.ui.presentation.old

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ca.kilbourne.isaac.lysts.data.domain.TodoList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityTopBar(
    toggleDrawer: () -> Unit,
    onDeleteListRequest: () -> Unit,
    onRenameListRequest: () -> Unit,
    selectedList: TodoList
) {
    var listOptionsExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = toggleDrawer) {
                Icon(Icons.Outlined.Menu, contentDescription = "Menu")
            }
        },
        title = {
            Text(selectedList.name)
        },
        actions = {
            Row {
                IconButton(onClick = { listOptionsExpanded = true }) {
                    Icon(Icons.Outlined.MoreVert, contentDescription = "Edit List")
                }
                DropdownMenu(
                    listOptionsExpanded,
                    onDismissRequest = { listOptionsExpanded = false }) {

                    DropdownMenuItem(
                        text = { Text("Remove All Checked") },
                        onClick = {
//                            selectedList.items.removeAll { it.done }; listOptionsExpanded = false
                        },
                        leadingIcon = { Icon(Icons.Outlined.Clear, contentDescription = null) }
                    )

                    HorizontalDivider()

                    DropdownMenuItem(
                        text = { Text("Rename List") },
                        onClick = {},
                        leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) })

                    DropdownMenuItem(
                        text = { Text("Delete List") },
                        colors = MenuDefaults.itemColors(MaterialTheme.colorScheme.error),
                        onClick = {},
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Delete,
                                tint = MaterialTheme.colorScheme.error,
                                contentDescription = null
                            )
                        })

                }
            }
        }
    )
}
