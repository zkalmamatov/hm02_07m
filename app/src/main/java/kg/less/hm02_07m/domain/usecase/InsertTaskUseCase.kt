package kg.less.hm02_07m.domain.usecase

import kg.less.hm02_07m.domain.model.TaskModel
import kg.less.hm02_07m.domain.repository.TaskManagerRepository
import java.time.LocalDateTime

class InsertTaskUseCase(private val taskManagerRepository: TaskManagerRepository) {

    suspend fun insertTask(taskModel: TaskModel): String {

        val existingTask = taskManagerRepository.getTaskByName(taskModel.taskName)
        if (existingTask != null) {
            return "Такое уже есть"
        }

        val taskDate = taskModel.taskDate.toIntOrNull()
        val currentHour = LocalDateTime.now().hour

        if (taskDate == null || taskDate < currentHour) {
            return "Ты че в прошлом живешь"
        }
        taskManagerRepository.insertTask(taskModel)
        return "Задача успешно добавлена"
    }
}