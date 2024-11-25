package kg.less.hm02_07m.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kg.less.hm02_07m.data.database.dto.TaskDto

@Dao
interface TaskManagerDao {
    @Insert(onConflict = REPLACE)
    suspend fun inserTask(taskDto: TaskDto)

    @Query("SELECT * FROM taskdto")
    suspend fun getAllNotes(): List<TaskDto>


    @Query("SELECT * FROM taskdto WHERE taskName = :taskName LIMIT 1")
    suspend fun getTaskByName(taskName: String): TaskDto?
}