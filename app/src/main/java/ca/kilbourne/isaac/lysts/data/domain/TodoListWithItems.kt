package ca.kilbourne.isaac.lysts.data.domain

import ca.kilbourne.isaac.lysts.persistence.room.relations.CurrentListWithItemsRelation
import ca.kilbourne.isaac.lysts.persistence.room.relations.TodoListWithItemsRelation

data class TodoListWithItems(val list: TodoList, val items: List<TodoItem>) {
    companion object {
        fun from(relation: TodoListWithItemsRelation?): TodoListWithItems? {
            if (relation == null) return null
            return fromNotNull(relation)
        }

        fun fromNotNull(relation: TodoListWithItemsRelation): TodoListWithItems {
            return TodoListWithItems(
                list = TodoList.fromNotNull(relation.list),
                items = relation.items.map(TodoItem::fromNotNull)
            )
        }

        fun from(relation: CurrentListWithItemsRelation?): TodoListWithItems? {
            if (relation == null) return null
            return fromNotNull(relation)
        }

        fun fromNotNull(relation: CurrentListWithItemsRelation): TodoListWithItems {
            return TodoListWithItems(
                list = TodoList.fromNotNull(relation.list),
                items = relation.items.map(TodoItem::fromNotNull)
            )
        }
    }
}
