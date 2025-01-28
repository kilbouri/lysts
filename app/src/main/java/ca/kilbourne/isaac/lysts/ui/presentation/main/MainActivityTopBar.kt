package ca.kilbourne.isaac.lysts.ui.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
    currentList: TodoList?,
    showListPicker: () -> Unit = {},
    onRemoveDoneRequest: () -> Unit = {},
    onRemoveAllRequest: () -> Unit = {},
    onNewListRequest: () -> Unit = {},
    onEditListRequest: () -> Unit = {},
    onDeleteListRequest: () -> Unit = {},
    onSettingsRequest: () -> Unit = {}
) {
    var appMenuExpanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = showListPicker) {
                Icon(Icons.Default.Menu, contentDescription = "Lists Menu")
            }
        },
        title = {
            if (currentList == null) LystsTitle()
            else TodoListTitle(currentList)
        },
        actions = {
            if (currentList != null) {
                Box() {
                    IconButton(onClick = { appMenuExpanded = !appMenuExpanded }) {
                        Icon(Icons.Default.MoreVert, "More")
                    }

                    MainActivityAppMenu(
                        expanded = appMenuExpanded,
                        onDismissRequest = { appMenuExpanded = false },
                        onRemoveDoneRequest = onRemoveDoneRequest,
                        onRemoveAllRequest = onRemoveAllRequest,
                        onNewListRequest = onNewListRequest,
                        onEditListRequest = onEditListRequest,
                        onDeleteListRequest = onDeleteListRequest,
                        onSettingsRequest = onSettingsRequest
                    )
                }
            }
        }
    )
}

@Composable
private fun LystsTitle() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            // TODO: real logo
            imageVector = Icons.Default.Check,
            contentDescription = "Lysts Logo"
        )

        Text("Lysts", modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
private fun TodoListTitle(list: TodoList) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Icon for List '${list.name}'"
        )

        Text(list.name, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
private fun MainActivityAppMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit = {},
    onRemoveDoneRequest: () -> Unit = {},
    onRemoveAllRequest: () -> Unit = {},
    onNewListRequest: () -> Unit = {},
    onEditListRequest: () -> Unit = {},
    onDeleteListRequest: () -> Unit = {},
    onSettingsRequest: () -> Unit = {}
) {
    @Composable
    fun AppMenuItem(
        icon: ImageVector,
        label: String,
        onClick: () -> Unit,
        color: Color = Color.Unspecified
    ) {
        val iconTint = if (color == Color.Unspecified) LocalContentColor.current else color

        DropdownMenuItem(
            leadingIcon = { Icon(icon, null, tint = iconTint) },
            text = { Text(label, color = color) },
            onClick = onClick
        )
    }

    DropdownMenu(expanded, onDismissRequest) {
        AppMenuItem(
            Icons.Default.Check,
            "Remove Done",
            onClick = onRemoveDoneRequest
        )
        AppMenuItem(
            Icons.Outlined.Delete,
            "Remove All",
            onClick = onRemoveAllRequest
        )

        HorizontalDivider(modifier = Modifier.padding(8.dp, 0.dp))

        AppMenuItem(Icons.Default.Add, "New List", onClick = onNewListRequest)
        AppMenuItem(Icons.Default.Edit, "Edit List", onClick = onEditListRequest)
        AppMenuItem(Icons.Outlined.Delete, "Delete List", onDeleteListRequest, color = Color.Red)

        HorizontalDivider(modifier = Modifier.padding(8.dp, 0.dp))

        AppMenuItem(Icons.Outlined.Settings, "Settings", onClick = onSettingsRequest)
    }
}
