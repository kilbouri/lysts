package ca.kilbourne.isaac.lysts.data.repositories

import ca.kilbourne.isaac.lysts.data.domain.TodoItem
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoItemDao

class TodoItemRepository(private val dao: TodoItemDao) {
    suspend fun create(item: TodoItem) = dao.insert(item.toEntity())
    suspend fun setCompletion(itemId: Long, done: Boolean) = dao.setCompletion(itemId, done)
    suspend fun delete(itemId: Long) = dao.delete(itemId)
    suspend fun rename(itemId: Long, newName: String) = dao.rename(itemId, newName)
    suspend fun removeCompleteFromList(listId: Long) = dao.clearListWithDoneState(listId, true)
    suspend fun removeAllFromList(listId: Long) = dao.clearList(listId)
}
