package ca.kilbourne.isaac.lysts.data.domain

import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity

data class TodoItem(
    val id: Long = 0,
    val listId: Long,
    val description: String,
    val done: Boolean = false
) {
    fun toEntity(): TodoItemEntity {
        return TodoItemEntity(id = id, listId = listId, description = description, done = done)
    }

    companion object {
        fun from(entity: TodoItemEntity?): TodoItem? {
            if (entity == null) return null
            return fromNotNull(entity)
        }

        fun fromNotNull(entity: TodoItemEntity): TodoItem {
            return TodoItem(
                id = entity.id,
                listId = entity.listId,
                description = entity.description,
                done = entity.done
            )
        }
    }
}
