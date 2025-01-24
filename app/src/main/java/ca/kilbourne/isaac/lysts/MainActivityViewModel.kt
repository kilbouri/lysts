package ca.kilbourne.isaac.lysts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListWithItems
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoItemDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoListDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoListWithItemsDao
import ca.kilbourne.isaac.lysts.persistence.room.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(getApplication())

    val todoLists = TodoListsProxy(db.todoListDao())
    val todoItems = TodoItemsProxy(db.todoItemDao())
    val todoListsWithItems = TodoListWithItemsProxy(db.todoListWithItemsDao())

    class TodoListsProxy(private val dao: TodoListDao) {
        fun getAll(): Flow<List<TodoList>> = dao.getAll().map { it.map(TodoList::fromEntity) }
        suspend fun create(newList: TodoList) = dao.insert(newList.toEntity())
        suspend fun delete(id: Long) = dao.delete(id)
        suspend fun rename(id: Long, newName: String) = dao.rename(id, newName)
    }

    class TodoItemsProxy(private val dao: TodoItemDao) {
        suspend fun insert(item: TodoItem) = dao.insert(item.toEntity())
        suspend fun delete(itemId: Long) = dao.delete(itemId)
        suspend fun rename(itemId: Long, newName: String) = dao.rename(itemId, newName)
    }

    class TodoListWithItemsProxy(private val dao: TodoListWithItemsDao) {
        fun get(listId: Long) = dao.get(listId).map(TodoListWithItems::fromRelation)
    }
}
