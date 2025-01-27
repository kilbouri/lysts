package ca.kilbourne.isaac.lysts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.kilbourne.isaac.lysts.data.TodoItem
import ca.kilbourne.isaac.lysts.data.TodoList
import ca.kilbourne.isaac.lysts.data.TodoListWithItems
import ca.kilbourne.isaac.lysts.persistence.room.dao.CurrentListDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoItemDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoListDao
import ca.kilbourne.isaac.lysts.persistence.room.database.AppDatabase
import ca.kilbourne.isaac.lysts.persistence.room.entities.CurrentListEntity
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(getApplication())

    val currentTodoList = CurrentTodoListProxy(db.currentListDao())
    val todoLists = TodoListsProxy(db.todoListDao())
    val todoItems = TodoItemsProxy(db.todoItemDao())

    class TodoListsProxy(private val dao: TodoListDao) {
        fun getWithItems(listId: Long) = dao.getWithItems(listId).map {
            if (it == null) null
            else TodoListWithItems.fromRelation(it)
        }

        fun getAll() = dao.getAll().map { it.map(TodoList::fromEntity) }
        suspend fun create(newList: TodoList) = dao.insert(newList.toEntity())
        suspend fun delete(id: Long) = dao.delete(id)
        suspend fun rename(id: Long, newName: String) = dao.rename(id, newName)
    }

    class TodoItemsProxy(private val dao: TodoItemDao) {
        suspend fun create(item: TodoItem) = dao.insert(item.toEntity())
        suspend fun delete(itemId: Long) = dao.delete(itemId)
        suspend fun rename(itemId: Long, newName: String) = dao.rename(itemId, newName)
    }

    class CurrentTodoListProxy(private val dao: CurrentListDao) {
        private companion object {
            const val DEFAULT_TRACKER_ID: Long = 0
        }

        suspend fun set(listId: Long) = dao.upsert(CurrentListEntity(DEFAULT_TRACKER_ID, listId))

        fun get() = dao.get(DEFAULT_TRACKER_ID).map {
            if (it == null) null
            else TodoList.fromEntity(it.list)
        }

        fun withItems() = dao.getWithItems(DEFAULT_TRACKER_ID).map {
            if (it == null) null
            else TodoListWithItems(
                list = TodoList.fromEntity(it.list),
                items = it.items.map(TodoItem::fromEntity)
            )
        }
    }
}
