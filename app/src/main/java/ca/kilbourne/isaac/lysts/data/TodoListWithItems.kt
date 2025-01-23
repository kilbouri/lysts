package ca.kilbourne.isaac.lysts.data

import ca.kilbourne.isaac.lysts.persistence.room.relations.TodoListWithItemsRelation

data class TodoListWithItems(val list: TodoList, val items: List<TodoListItem>) {
    companion object {
        fun fromRelation(relation: TodoListWithItemsRelation): TodoListWithItems {
            return TodoListWithItems(
                list = TodoList.fromEntity(relation.list),
                items = relation.items.map { TodoListItem.fromEntity(it) }
            )
        }
    }
}
