package ca.kilbourne.isaac.lysts.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ShoppingCart

sealed class Placeholder {
    sealed class TodoLists {
        companion object {
            fun androidFlavors(): TodoList {
                return TodoList(
                    name = "Android Flavors",
                    icon = Icons.Outlined.Build,
                    items = TodoListItems.androidFlavors().toMutableList()
                )
            }

            fun groceries(): TodoList {
                return TodoList(
                    name = "Groceries",
                    icon = Icons.Outlined.ShoppingCart,
                    items = TodoListItems.groceries().toMutableList()
                )
            }

            fun movies(): TodoList {
                return TodoList(
                    name = "Movies to Watch",
                    icon = Icons.Outlined.PlayArrow,
                    items = TodoListItems.movies().toMutableList()
                )
            }

            fun places(): TodoList {
                return TodoList(
                    name = "Places to Visit",
                    icon = Icons.Outlined.Place,
                    items = TodoListItems.places().toMutableList()
                )
            }

            fun empty(): TodoList {
                return TodoList(
                    name = "Empty Placeholder List",
                    icon = Icons.Outlined.Info,
                    items = mutableListOf()
                )
            }
        }
    }

    sealed class TodoListItems {
        companion object {
            fun androidFlavors(): List<TodoListItem> {
                return listOf(
                    TodoListItem(description = "Cupcake", done = false),
                    TodoListItem(description = "Donut", done = false),
                    TodoListItem(description = "Eclair", done = false),
                    TodoListItem(description = "Froyo", done = false),
                    TodoListItem(description = "Gingerbread", done = false),
                    TodoListItem(description = "Honeycomb", done = false),
                    TodoListItem(description = "Ice Cream Sandwich", done = false),
                    TodoListItem(description = "Jelly Bean", done = false),
                    TodoListItem(description = "KitKat", done = false),
                    TodoListItem(description = "Lollipop", done = false),
                    TodoListItem(description = "Marshmallow", done = false),
                    TodoListItem(description = "Nougat", done = false),
                    TodoListItem(description = "Oreo", done = false),
                    TodoListItem(description = "Pie", done = false),
                    TodoListItem(description = "Snow Cone", done = false),
                    TodoListItem(description = "Tiramisu", done = false),
                    TodoListItem(description = "Upside Down Cake", done = false),
                    TodoListItem(description = "Vanilla Ice Cream", done = false)
                )
            }


            fun groceries(): List<TodoListItem> {
                return listOf(
                    TodoListItem(description = "Apples", done = false),
                    TodoListItem(description = "Bananas", done = false),
                    TodoListItem(description = "Strawberries", done = false),
                    TodoListItem(description = "Avocados", done = false),
                    TodoListItem(description = "Bell Peppers", done = false),
                    TodoListItem(description = "Carrot", done = false),
                    TodoListItem(description = "Garlic", done = false),
                    TodoListItem(description = "Lemons/Limes", done = false),
                    TodoListItem(description = "Onion", done = false),
                    TodoListItem(description = "Parsley", done = false),
                    TodoListItem(description = "Cilantro", done = false),
                    TodoListItem(description = "Potatoes", done = false),
                    TodoListItem(description = "Spinach", done = false),
                    TodoListItem(description = "Tomatoes", done = false),
                )
            }

            fun movies(): List<TodoListItem> {
                return listOf(
                    TodoListItem(description = "The Shawshank Redemption", done = false),
                    TodoListItem(description = "The Godfather", done = false),
                    TodoListItem(description = "The Dark Knight", done = false),
                    TodoListItem(description = "The Godfather Part II", done = false),
                    TodoListItem(description = "12 Angry Men", done = false),
                    TodoListItem(
                        description = "The Lord of the Rings: The Return of the King", done = false
                    ),
                    TodoListItem(description = "Schindler's List", done = false),
                    TodoListItem(description = "Pulp Fiction", done = false),
                    TodoListItem(
                        description = "The Lord of the Rings: The Fellowship of the Ring",
                        done = false
                    ),
                    TodoListItem(description = "The Good, the Bad and the Ugly", done = false),
                    TodoListItem(description = "Forrest Gump", done = false),
                    TodoListItem(
                        description = "The Lord of the Rings: The Two Towers", done = false
                    ),
                    TodoListItem(description = "Fight Club", done = false),
                    TodoListItem(description = "Inception", done = false),
                    TodoListItem(
                        description = "Star Wars: Episode V - The Empire Strikes Back", done = false
                    ),
                    TodoListItem(description = "The Matrix", done = false),
                    TodoListItem(description = "Goodfellas", done = false),
                    TodoListItem(description = "One Flew Over the Cuckoo's Nest", done = false),
                    TodoListItem(description = "Interstellar", done = false),
                    TodoListItem(description = "Se7en", done = false),
                    TodoListItem(description = "It's a Wonderful Life", done = false),
                    TodoListItem(description = "Seven Samurai", done = false),
                    TodoListItem(description = "The Silence of the Lambs", done = false),
                    TodoListItem(description = "Saving Private Ryan", done = false),
                )
            }

            fun places(): List<TodoListItem> {
                return listOf(
                    TodoListItem(description = "Paris", done = false),
                    TodoListItem(description = "Bora Bora", done = false),
                    TodoListItem(description = "Glacier National Park", done = false),
                    TodoListItem(description = "Rome", done = false),
                    TodoListItem(description = "Swiss Alps", done = false),
                    TodoListItem(description = "Maui", done = false),
                    TodoListItem(description = "London, England", done = false),
                    TodoListItem(description = "Maldives", done = false),
                    TodoListItem(description = "Turks & Caicos", done = false),
                    TodoListItem(description = "Belongs on List?", done = false),
                    TodoListItem(description = "Tokyo", done = false),
                )
            }
        }
    }
}


