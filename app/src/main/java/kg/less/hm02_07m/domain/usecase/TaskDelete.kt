package kg.less.hm02_07m.domain.usecase

import kg.less.hm02_07m.domain.model.TaskModel
import kg.less.hm02_07m.domain.repository.TaskManagerRepository

class TaskDelete(private val taskManagerRepository: TaskManagerRepository) {

    suspend fun deleteTask(taskModel: TaskModel) {
        taskManagerRepository.deleteTask(taskModel)
    }
}