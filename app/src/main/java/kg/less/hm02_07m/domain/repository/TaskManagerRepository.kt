package kg.less.hm02_07m.domain.repository

import kg.less.hm02_07m.data.database.dto.TaskDto

interface TaskManagerRepository {

    suspend fun insertTask(taskDto: TaskDto)
    suspend fun getAllNotes(): List<TaskDto>
    suspend fun getTaskByName(taskName: String): TaskDto?
}