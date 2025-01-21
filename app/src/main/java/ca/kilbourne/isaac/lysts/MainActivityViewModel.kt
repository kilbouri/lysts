package ca.kilbourne.isaac.lysts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.persistence.room.database.AppDatabase
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(getApplication())
    private val todoLists = db.todoListDao()

    val allTodoLists: Flow<List<TodoList>> = todoLists.getAll().map {
        it.map { TodoList(id = it.id, name = it.name) }
    }

    suspend fun addNewList(name: String) {
        todoLists.insert(TodoListEntity(name = name))
    }

    suspend fun removeList(id: Long) {
        todoLists.delete(id)
    }
}
