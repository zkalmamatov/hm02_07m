package kg.less.hm02_07m.presentation.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.less.hm02_07m.domain.usecase.GetTaskUseCase
import kg.less.hm02_07m.domain.usecase.UpdateTaskUseCase
import kg.less.hm02_07m.presentation.model.TaskUI
import kg.less.hm02_07m.presentation.model.toDomain
import kg.less.hm02_07m.presentation.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    private val _updateMessageStateFlow = MutableStateFlow(String())
    val updateMessageFlow: StateFlow<String> = _updateMessageStateFlow

    suspend fun getTask(id: Int) = getTaskUseCase(id)?.toUi()

    fun updateTask(taskUI: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.updateTask(taskUI.toDomain())
        }
    }
}