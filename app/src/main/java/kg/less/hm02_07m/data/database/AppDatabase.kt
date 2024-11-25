package kg.less.hm02_07m.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.less.hm02_07m.data.database.dao.TaskManagerDao
import kg.less.hm02_07m.data.database.dto.TaskDto

@Database(entities = [TaskDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskManagerDao(): TaskManagerDao
}