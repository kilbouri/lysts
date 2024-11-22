package ca.kilbourne.isaac.lysts

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.lifecycle.AndroidViewModel
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.persistence.room.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(getApplication())
    private val todoLists = db.todoListDao()

    val allTodoLists: Flow<List<TodoList>> = todoLists
        .getAll()
        .map {
            it.map {
                TodoList(
                    name = it.name,
                    icon = Icons.Outlined.MoreVert,
                    items = mutableListOf()
                )
            }
        }
}