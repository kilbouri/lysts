package ca.kilbourne.isaac.lysts.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity
import ca.kilbourne.isaac.lysts.persistence.room.relations.TodoListWithItemsRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {
    @Transaction
    @Query("SELECT * FROM todo_lists WHERE id = :listId")
    fun getWithItems(listId: Long): Flow<TodoListWithItemsRelation?>

    @Query("SELECT * FROM todo_lists")
    fun getAll(): Flow<List<TodoListEntity>>

    @Insert
    suspend fun insert(list: TodoListEntity): Long

    @Query("DELETE FROM todo_lists WHERE id = :listId")
    suspend fun delete(listId: Long)

    @Query("UPDATE todo_lists SET name = :newName WHERE id = :listId")
    suspend fun rename(listId: Long, newName: String)
}
