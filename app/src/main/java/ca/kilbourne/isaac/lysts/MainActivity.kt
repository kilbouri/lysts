package ca.kilbourne.isaac.lysts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import ca.kilbourne.isaac.lysts.data.Placeholder
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.ui.presentation.main.MainActivityPresentation
import ca.kilbourne.isaac.lysts.ui.theme.LystsTheme

class MainActivity : ComponentActivity() {

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val lists = remember {
                mutableStateListOf(
                    TodoList(
                        "Groceries",
                        icon = Icons.Outlined.ShoppingCart,
                        Placeholder.TodoListItems.groceries().toMutableStateList()
                    ), TodoList(
                        "Movies to Watch",
                        icon = Icons.Outlined.PlayArrow,
                        Placeholder.TodoListItems.movies().toMutableStateList()
                    ), TodoList(
                        "Places to Visit",
                        icon = Icons.Outlined.Place,
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

            // TODO: move all the presentation code to another file; this method should
            //       concern itself with data loading and rendering our presentation
            LystsTheme {
                MainActivityPresentation(todoLists = lists)
            }
        }
    }
}