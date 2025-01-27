package ca.kilbourne.isaac.lysts.persistence.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.kilbourne.isaac.lysts.persistence.room.entities.CurrentListEntity
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity

data class CurrentListWithItemsRelation(
    @Embedded private val currentListEntity: CurrentListEntity,

    @Relation(
        parentColumn = CurrentListEntity.Columns.LIST_ID,
        entityColumn = TodoListEntity.Columns.ID
    )
    val list: TodoListEntity,

    @Relation(
        parentColumn = CurrentListEntity.Columns.LIST_ID,
        entityColumn = TodoItemEntity.Columns.LIST_ID
    )
    val items: List<TodoItemEntity>
)
