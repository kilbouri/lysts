package ca.kilbourne.isaac.lysts.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity

@Dao
interface TodoItemDao {
    @Insert
    suspend fun insert(item: TodoItemEntity)

    @Query("UPDATE todo_items SET is_done = :done WHERE id = :itemId")
    suspend fun setCompletion(itemId: Long, done: Boolean)

    @Query("DELETE FROM todo_items WHERE id = :itemId")
    suspend fun delete(itemId: Long)

    @Query("UPDATE todo_lists SET name = :newName WHERE id = :itemId")
    suspend fun rename(itemId: Long, newName: String)

    @Query("DELETE FROM todo_items WHERE list_id = :listId AND is_done = :done")
    suspend fun clearListWithDoneState(listId: Long, done: Boolean)

    @Query("DELETE FROM todo_items WHERE list_id = :listId")
    suspend fun clearList(listId: Long)
}
