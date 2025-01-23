package ca.kilbourne.isaac.lysts.persistence.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListWithItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListWithItemsDao {

    @Transaction
    @Query("SELECT * FROM todo_lists WHERE id = :listId")
    fun getTodoListWithItems(listId: Long): Flow<TodoListWithItemsEntity>
}
