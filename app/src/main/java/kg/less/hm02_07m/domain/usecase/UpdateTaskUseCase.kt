package kg.less.hm02_07m.domain.usecase

import kg.less.hm02_07m.domain.model.TaskModel
import kg.less.hm02_07m.domain.repository.TaskManagerRepository

class UpdateTaskUseCase(private val taskRepository: TaskManagerRepository) {

    suspend fun updateTask(taskModel: TaskModel): String {
        return try {
            taskRepository.updateTask(taskModel)
            "Все ок!"
        }catch (e: Exception) {
            "Ошибка : ${e.message}"
        }
    }
}