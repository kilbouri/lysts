package ca.kilbourne.isaac.lysts.data

import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity

data class TodoList(val id: Long = 0, val name: String) {

    fun toEntity(): TodoListEntity {
        return TodoListEntity(id = id, name = name)
    }

    companion object {
        fun from(entity: TodoListEntity?): TodoList? {
            if (entity == null) return null
            return fromNotNull(entity)
        }

        fun fromNotNull(entity: TodoListEntity): TodoList {
            return TodoList(id = entity.id, name = entity.name)
        }
    }
}
