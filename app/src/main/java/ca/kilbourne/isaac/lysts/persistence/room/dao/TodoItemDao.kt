package ca.kilbourne.isaac.lysts.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity

@Dao
interface TodoItemDao {
    @Insert
    suspend fun insert(item: TodoItemEntity)

    @Query("DELETE FROM todo_items WHERE id = :itemId")
    suspend fun delete(itemId: Long)

    @Query("UPDATE todo_lists SET name = :newName WHERE id = :itemId")
    suspend fun rename(itemId: Long, newName: String)
}
