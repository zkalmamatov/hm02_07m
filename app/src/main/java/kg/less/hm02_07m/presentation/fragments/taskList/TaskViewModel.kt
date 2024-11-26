package kg.less.hm02_07m.presentation.fragments.taskList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.less.hm02_07m.domain.usecase.GetAllNotesUseCase
import kg.less.hm02_07m.domain.usecase.GetTaskUseCase
import kg.less.hm02_07m.domain.usecase.InsertTaskUseCase
import kg.less.hm02_07m.domain.usecase.TaskDelete
import kg.less.hm02_07m.domain.usecase.UpdateTaskUseCase
import kg.less.hm02_07m.presentation.fragments.base.BaseViewModel
import kg.less.hm02_07m.presentation.model.TaskUI
import kg.less.hm02_07m.presentation.model.toDomain
import kg.less.hm02_07m.presentation.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val delete: TaskDelete,
    private val getTaskUseCase: GetTaskUseCase
) : BaseViewModel() {

    private val _tasksStateFlow = MutableStateFlow<List<TaskUI>>(emptyList())
    val taskFlow: StateFlow<List<TaskUI>> = _tasksStateFlow.asStateFlow()

    fun fetchTask() {
        runLaunchIO() {
            val task = getTaskUseCase(1)
            task?.let {

            }
        }
    }

    fun loadTasks() {
        runLaunchIO {
            getAllNotesUseCase().onEach { taskList ->
                _tasksStateFlow.value = taskList.map { it.toUi() }
            }.collect()
        }
    }

    fun deleteTask(taskUI: TaskUI) {
        viewModelScope.launch {
            try {
                delete.deleteTask(taskUI.toDomain())
                loadTasks()
            } catch (e: Exception) {
                Log.e("Kick_Down", "Error: ${e.message}")
            }
        }
    }
}