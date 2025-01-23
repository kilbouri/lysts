package ca.kilbourne.isaac.lysts.persistence.room.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TodoListWithItemsEntity(
    @Embedded val list: TodoListEntity,
    @Relation(
        parentColumn = TodoListEntity.Columns.ID,
        entityColumn = TodoItemEntity.Columns.LIST_ID
    )
    val items: List<TodoItemEntity>
)
