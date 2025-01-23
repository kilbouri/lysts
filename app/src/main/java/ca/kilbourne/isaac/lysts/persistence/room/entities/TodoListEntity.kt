package ca.kilbourne.isaac.lysts.persistence.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_lists")
data class TodoListEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = Columns.ID) val id: Long = 0,
    @ColumnInfo(name = Columns.NAME) val name: String
) {
    object Columns {
        const val ID = "id"
        const val NAME = "name"
    }
}
