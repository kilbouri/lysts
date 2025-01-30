package ca.kilbourne.isaac.lysts.persistence.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ca.kilbourne.isaac.lysts.persistence.room.relations.CurrentListRelation
import ca.kilbourne.isaac.lysts.persistence.room.relations.CurrentListWithItemsRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentListDao {

    @Transaction
    @Query("SELECT * FROM current_lists WHERE id = 0")
    fun get(): Flow<CurrentListRelation?>

    @Transaction
    @Query("SELECT * FROM current_lists WHERE id = 0")
    fun getWithItems(): Flow<CurrentListWithItemsRelation?>

    @Query("INSERT OR REPLACE INTO current_lists(id, list_id) VALUES (0, :listId)")
    suspend fun set(listId: Long)

    @Query("DELETE FROM current_lists WHERE id = 0")
    suspend fun delete()
}
