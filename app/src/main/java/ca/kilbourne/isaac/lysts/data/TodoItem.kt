package ca.kilbourne.isaac.lysts.data

import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity

data class TodoItem(
    val id: Long? = null,
    val listId: Long,
    val description: String,
    val done: Boolean
) {
    fun toEntity(): TodoItemEntity {
        return TodoItemEntity(id = id ?: 0, listId = listId, description = description, done = done)
    }

    companion object {
        fun fromEntity(entity: TodoItemEntity): TodoItem {
            return TodoItem(
                id = entity.id,
                listId = entity.listId,
                description = entity.description,
                done = entity.done
            )
        }
    }
}
