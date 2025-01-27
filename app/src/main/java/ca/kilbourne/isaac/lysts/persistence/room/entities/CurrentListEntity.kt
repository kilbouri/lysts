package ca.kilbourne.isaac.lysts.persistence.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "current_lists",
    indices = [Index(CurrentListEntity.Columns.LIST_ID)],
    foreignKeys = [ForeignKey(
        entity = TodoListEntity::class,
        parentColumns = [TodoListEntity.Columns.ID],
        childColumns = [CurrentListEntity.Columns.LIST_ID],
        deferred = true
    )]
)
data class CurrentListEntity(
    @PrimaryKey @ColumnInfo(name = Columns.ID) val id: Long,
    @ColumnInfo(name = Columns.LIST_ID) val listId: Long
) {
    object Columns {
        const val ID = "id"
        const val LIST_ID = "list_id"
    }
}
