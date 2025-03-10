package ca.kilbourne.isaac.lysts.persistence.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "todo_items",
    indices = [Index(TodoItemEntity.Columns.LIST_ID)],
    foreignKeys = [ForeignKey(
        entity = TodoListEntity::class,
        parentColumns = [TodoListEntity.Columns.ID],
        childColumns = [TodoItemEntity.Columns.LIST_ID]
    )]
)
data class TodoItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = Columns.ID) val id: Long = 0,
    @ColumnInfo(name = Columns.LIST_ID) val listId: Long,
    @ColumnInfo(name = Columns.DESCRIPTION) val description: String,
    @ColumnInfo(name = Columns.IS_DONE) val done: Boolean = false
) {
    object Columns {
        const val ID = "id"
        const val LIST_ID = "list_id"
        const val DESCRIPTION = "description"
        const val IS_DONE = "is_done"
    }
}
