package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import ca.kilbourne.isaac.lysts.data.Placeholder
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityPresentation
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val lists = remember {
                mutableStateListOf(
                    TodoList(
                        "Android Flavors",
                        Icons.Outlined.Build,
                        Placeholder.TodoListItems.androidFlavors().toMutableStateList()
                    ),
                    TodoList(
                        "Groceries",
                        Icons.Outlined.ShoppingCart,
                        Placeholder.TodoListItems.groceries().toMutableStateList()
                    ), TodoList(
                        "Movies to Watch",
                        Icons.Outlined.PlayArrow,
                        Placeholder.TodoListItems.movies().toMutableStateList()
                    ), TodoList(
                        "Places to Visit",
                        Icons.Outlined.Place,
                        Placeholder.TodoListItems.places().toMutableStateList()
                    )
                )
            }

            // Navbar should contain a VertMenu with the following options:
            // - rename list
            // - change list icon (do we just enumerate Icons.Outlined? What about custom SVGs?)
            // - delete list
            // - horizontal. sep
            // - delete all finished items from current list

            LystsTheme {
                MainActivityPresentation(todoLists = lists)
            }
        }
    }
}