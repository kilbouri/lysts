package ca.kilbourne.isaac.lysts.data.repositories

import ca.kilbourne.isaac.lysts.data.domain.TodoList
import ca.kilbourne.isaac.lysts.data.domain.TodoListWithItems
import ca.kilbourne.isaac.lysts.persistence.room.dao.CurrentListDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoListDao
import ca.kilbourne.isaac.lysts.persistence.room.relations.CurrentListRelation
import kotlinx.coroutines.flow.map

class TodoListRepository(
    private val listDao: TodoListDao,
    private val currentListDao: CurrentListDao
) {
    fun getAll() = listDao.getAll().map { it.map(TodoList::fromNotNull) }
    fun current() = currentListDao.get().map<CurrentListRelation?, _> { TodoList.from(it?.list) }
    fun currentWithItems() = currentListDao.getWithItems().map(TodoListWithItems::from)

    suspend fun setCurrent(listId: Long) = currentListDao.set(listId)

    suspend fun create(newList: TodoList) = listDao.insert(newList.toEntity())
    suspend fun rename(id: Long, newName: String) = listDao.rename(id, newName)
}
