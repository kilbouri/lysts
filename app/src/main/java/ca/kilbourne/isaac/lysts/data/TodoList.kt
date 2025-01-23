package ca.kilbourne.isaac.lysts.data

import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity

data class TodoList(val id: Long? = null, val name: String) {

    fun toEntity(): TodoListEntity {
        return TodoListEntity(id = id ?: 0, name = name)
    }

    companion object {
        fun fromEntity(entity: TodoListEntity): TodoList {
            return TodoList(id = entity.id, name = entity.name)
        }
    }
}
