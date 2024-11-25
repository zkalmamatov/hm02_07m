package kg.less.hm02_07m.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import kg.less.hm02_07m.domain.model.TaskModel

@Entity
data class TaskDto (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskName: String? = null,
    val taskDate: String? = null
)

fun TaskModel.toData() = TaskDto(id, taskName, taskDate)