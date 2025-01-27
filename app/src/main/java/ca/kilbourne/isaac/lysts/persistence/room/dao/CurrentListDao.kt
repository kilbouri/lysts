package ca.kilbourne.isaac.lysts.persistence.room.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ca.kilbourne.isaac.lysts.persistence.room.entities.CurrentListEntity
import ca.kilbourne.isaac.lysts.persistence.room.relations.CurrentListRelation
import ca.kilbourne.isaac.lysts.persistence.room.relations.CurrentListWithItemsRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentListDao {

    @Transaction
    @Query("SELECT * FROM current_lists WHERE id = :itemId")
    fun get(itemId: Long): Flow<CurrentListRelation?>

    @Transaction
    @Query("SELECT * FROM current_lists WHERE id = :itemId")
    fun getWithItems(itemId: Long): Flow<CurrentListWithItemsRelation?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: CurrentListEntity)

    @Query("DELETE FROM current_lists WHERE id = :itemId")
    suspend fun delete(itemId: Long)
}
