package kg.less.hm02_07m.data.database.repositorylmpl

import kg.less.hm02_07m.data.database.dao.TaskManagerDao
import kg.less.hm02_07m.data.database.dto.TaskDto
import kg.less.hm02_07m.domain.repository.TaskManagerRepository

class TaskManagerRepositoryImpl(
    private val taskManagerDao: TaskManagerDao
) : TaskManagerRepository {

    override suspend fun insertTask(taskDto: TaskDto) {
        taskManagerDao.inserTask(taskDto)
    }

    override suspend fun getAllNotes(): List<TaskDto> {
        return taskManagerDao.getAllNotes()
    }

    override suspend fun getTaskByName(taskName: String): TaskDto? {
        return taskManagerDao.getTaskByName(taskName)
    }
}