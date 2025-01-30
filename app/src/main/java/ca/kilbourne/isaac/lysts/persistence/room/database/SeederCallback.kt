package ca.kilbourne.isaac.lysts.persistence.room.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.kilbourne.isaac.lysts.persistence.room.entities.TodoListEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeederCallback(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        // We run this coroutine on the main thread so it blocks app
        // startup during db creation
        CoroutineScope(Dispatchers.Main).launch {
            val appDb = AppDatabase.getDatabase(context);

            addFirstList(appDb)
        }
    }

    private suspend fun addFirstList(db: AppDatabase) {
        val initialListId = db.todoListDao().insert(TodoListEntity(name = "To-Do List"))
        db.currentListDao().set(initialListId)
    }
}
