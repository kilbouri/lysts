package ca.kilbourne.isaac.lysts.persistence.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.kilbourne.isaac.lysts.persistence.room.dao.CurrentListDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoItemDao
import ca.kilbourne.isaac.lysts.persistence.room.dao.TodoListDao
import ca.kilbourne.isaac.lysts.persistence.room.entities.CurrentListEntity
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoItemEntity
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity

@Database(
    entities = [TodoListEntity::class, TodoItemEntity::class, CurrentListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoListDao(): TodoListDao
    abstract fun todoItemDao(): TodoItemDao
    abstract fun currentListDao(): CurrentListDao

    companion object {
        @Volatile
        private var _instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return _instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_list_database"
                )
                    .addCallback(SeederCallback(context))
                    .build()

                _instance = instance
                instance
            }
        }
    }
}
