package kg.less.hm02_07m.domain.repository

import kg.less.hm02_07m.data.dto.TaskDto
import kg.less.hm02_07m.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskManagerRepository {

    suspend fun insertTask(taskModel: TaskModel)
    suspend fun getAllNotes(): Flow<List<TaskModel>>
    suspend fun getTaskByName(taskName: String): TaskModel?

    suspend fun updateTask(taskModel: TaskModel)
    suspend fun deleteTask(task: TaskModel)
    suspend fun getTask(id: Int): TaskModel?

}