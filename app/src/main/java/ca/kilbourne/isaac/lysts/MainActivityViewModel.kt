package ca.kilbourne.isaac.lysts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListItem
import ca.kilbourne.isaac.lysts.data.TodoListWithItems
import ca.kilbourne.isaac.lysts.persistence.room.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(getApplication())

    val todoLists = object {
        private val dao = db.todoListDao()

        fun getAll(): Flow<List<TodoList>> = dao.getAll().map { it.map(TodoList::fromEntity) }
        suspend fun create(newList: TodoList) = dao.insert(newList.toEntity())
        suspend fun delete(id: Long) = dao.delete(id)
        suspend fun rename(id: Long, newName: String) = dao.rename(id, newName)
    }

    val todoItems = object {
        private val dao = db.todoItemDao()

        suspend fun insert(item: TodoListItem) = dao.insert(item.toEntity())
        suspend fun delete(itemId: Long) = dao.delete(itemId)
        suspend fun rename(itemId: Long, newName: String) = dao.rename(itemId, newName)
    }

    val todoListsWithItems = object {
        private val dao = db.todoListWithItemsDao()

        fun get(listId: Long) = dao.get(listId).map(TodoListWithItems::fromRelation)
    }
}
