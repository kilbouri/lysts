package ca.kilbourne.isaac.lysts.persistence.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.kilbourne.isaac.lysts.persistence.room.entities.CurrentListEntity
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity

data class CurrentListRelation(
    @Embedded private val currentListEntity: CurrentListEntity,
    @Relation(
        parentColumn = CurrentListEntity.Columns.LIST_ID,
        entityColumn = TodoListEntity.Columns.ID
    )
    val list: TodoListEntity
)
