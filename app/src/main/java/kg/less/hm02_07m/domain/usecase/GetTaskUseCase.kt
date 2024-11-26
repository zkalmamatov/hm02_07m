package kg.less.hm02_07m.domain.usecase

import kg.less.hm02_07m.domain.model.TaskModel
import kg.less.hm02_07m.domain.repository.TaskManagerRepository

class GetTaskUseCase (
    private val taskManagerRepository: TaskManagerRepository) {

    suspend operator fun invoke(id:Int): TaskModel?{
        return taskManagerRepository.getTask(id)
    }
}