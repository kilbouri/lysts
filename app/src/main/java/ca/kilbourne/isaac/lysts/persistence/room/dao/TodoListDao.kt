package ca.kilbourne.isaac.lysts.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoListDao {
    @Insert
    suspend fun insert(list: TodoListEntity)

    @Query("SELECT * FROM todo_lists")
    fun getAll(): Flow<List<TodoListEntity>>
}