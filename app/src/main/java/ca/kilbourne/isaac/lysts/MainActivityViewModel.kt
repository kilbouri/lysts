package ca.kilbourne.isaac.lysts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ca.kilbourne.isaac.lysts.data.domain.TodoItem
import ca.kilbourne.isaac.lysts.data.repositories.TodoItemRepository
import ca.kilbourne.isaac.lysts.data.repositories.TodoListRepository
import ca.kilbourne.isaac.lysts.persistence.room.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(getApplication())

    val todoLists = TodoListRepository(db.todoListDao(), db.currentListDao())
    val todoItems = TodoItemRepository(db.todoItemDao())

    fun onItemCompletionChange(coroutineScope: CoroutineScope): (TodoItem, Boolean) -> Unit {
        return { item, done ->
            coroutineScope.launch {
                todoItems.setCompletion(item.id, done)
            }
        }
    }

    fun onAddItemRequest(listId: Long?, coroutineScope: CoroutineScope): () -> Unit {
        return {
            coroutineScope.launch {
                if (listId == null) return@launch
                todoItems.create(TodoItem(listId = listId, description = "Item"))
            }
        }
    }

    fun onRemoveDoneRequest(listId: Long?, coroutineScope: CoroutineScope): () -> Unit {
        return {
            coroutineScope.launch {
                if (listId == null) return@launch
                todoItems.removeCompleteFromList(listId)
            }
        }
    }

    fun onRemoveAllRequest(listId: Long?, coroutineScope: CoroutineScope): () -> Unit {
        return {
            coroutineScope.launch {
                if (listId == null) return@launch
                todoItems.removeAllFromList(listId)
            }
        }
    }
}
