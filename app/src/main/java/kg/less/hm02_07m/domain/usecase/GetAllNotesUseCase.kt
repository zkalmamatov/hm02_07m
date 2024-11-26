package kg.less.hm02_07m.domain.usecase

import kg.less.hm02_07m.domain.model.TaskModel
import kg.less.hm02_07m.domain.repository.TaskManagerRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase (
    private val taskManagerRepository: TaskManagerRepository
) {

    suspend operator fun invoke(): Flow<List<TaskModel>> {
        return taskManagerRepository.getAllNotes()
    }
}