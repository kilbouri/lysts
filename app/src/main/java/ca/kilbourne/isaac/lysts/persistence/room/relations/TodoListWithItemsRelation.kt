package ca.kilbourne.isaac.lysts.persistence.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity

data class TodoListWithItemsRelation(
    @Embedded val list: TodoListEntity,
    @Relation(
        parentColumn = TodoListEntity.Columns.ID,
        entityColumn = TodoItemEntity.Columns.LIST_ID
    )
    val items: List<TodoItemEntity>
)
