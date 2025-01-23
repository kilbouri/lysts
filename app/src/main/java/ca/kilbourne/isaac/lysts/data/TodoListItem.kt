package ca.kilbourne.isaac.lysts.data

import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity

data class TodoListItem(
    val id: Long? = null,
    val listId: Long,
    val description: String,
    val done: Boolean
) {
    fun toEntity(): TodoItemEntity {
        return TodoItemEntity(id = id ?: 0, listId = listId, description = description, done = done)
    }

    companion object {
        fun fromEntity(entity: TodoItemEntity): TodoListItem {
            return TodoListItem(
                id = entity.id,
                listId = entity.listId,
                description = entity.description,
                done = entity.done
            )
        }
    }
}
