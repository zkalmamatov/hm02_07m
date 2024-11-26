package kg.less.hm02_07m.presentation.fragments.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.less.hm02_07m.domain.usecase.GetAllNotesUseCase
import kg.less.hm02_07m.domain.usecase.GetTaskUseCase
import kg.less.hm02_07m.domain.usecase.InsertTaskUseCase
import kg.less.hm02_07m.domain.usecase.TaskDelete
import kg.less.hm02_07m.domain.usecase.UpdateTaskUseCase
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
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val delete: TaskDelete,
    private val getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    private val _tasksStateFlow = MutableStateFlow<List<TaskUI>>(emptyList())
    val taskFlow: StateFlow<List<TaskUI>> = _tasksStateFlow.asStateFlow()

    private val _insertMessageStateFlow = MutableStateFlow(String())
    val insertMessageFlow: StateFlow<String> = _insertMessageStateFlow.asStateFlow()

    private val _updateMessageStateFlow = MutableStateFlow(String())
    val updateMessageFlow: StateFlow<String> = _updateMessageStateFlow

    fun insertTask(taskUI: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = insertTaskUseCase.insertTask(taskUI.toDomain())
            _insertMessageStateFlow.value = message
        }
    }

    fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllNotesUseCase().onEach {
                _tasksStateFlow.value = it.map { model -> model.toUi() }
            }.collect()
        }
    }

    suspend fun getTask(id: Int) = getTaskUseCase(id)?.toUi()

    fun updateTask(taskUI: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.updateTask(taskUI.toDomain())
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