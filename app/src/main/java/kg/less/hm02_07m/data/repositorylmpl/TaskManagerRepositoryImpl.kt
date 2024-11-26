package kg.less.hm02_07m.data.repositorylmpl

import kg.less.hm02_07m.data.database.dao.TaskManagerDao
import kg.less.hm02_07m.data.dto.toData
import kg.less.hm02_07m.data.dto.toDomain
import kg.less.hm02_07m.domain.model.TaskModel
import kg.less.hm02_07m.domain.repository.TaskManagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskManagerRepositoryImpl(
    private val taskManagerDao: TaskManagerDao
) : TaskManagerRepository {

    override suspend fun insertTask(taskModel: TaskModel) {
        taskManagerDao.insertTask(taskModel.toData())
    }

    override suspend fun getAllNotes():  Flow<List<TaskModel>> {
        return taskManagerDao.getAllNotes().map {list ->
            list.map {dto->
                dto.toDomain()
            }
        }
    }

    override suspend fun getTaskByName(taskName: String): TaskModel? {
        return taskManagerDao.getTaskByName(taskName)?.toDomain()
    }

    override suspend fun updateTask(taskModel: TaskModel) {
        taskManagerDao.updateTask(taskModel.toData())
    }

    override suspend fun deleteTask(task: TaskModel) {
        taskManagerDao.deleteTask(task.toData())
    }

    override suspend fun getTask(id: Int): TaskModel? {
        val taskDto = taskManagerDao.getTaskById(id)
        return taskDto?.toDomain()
    }
}