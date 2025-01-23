package ca.kilbourne.isaac.lysts.persistence.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoItemDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoListDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoListWithItemsDao
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity

@Database(
    entities = [TodoListEntity::class, TodoItemEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoListDao(): TodoListDao
    abstract fun todoItemDao(): TodoItemDao
    abstract fun todoListWithItemsDao(): TodoListWithItemsDao

    companion object {
        @Volatile
        private var _instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return _instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_list_database"
                ).build()

                _instance = instance
                instance
            }
        }
    }
}
