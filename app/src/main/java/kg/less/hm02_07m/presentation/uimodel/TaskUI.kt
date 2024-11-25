package kg.less.hm02_07m.presentation.uimodel

import kg.less.hm02_07m.domain.model.TaskModel

data class TaskUI (
    val id: Int,
    val taskName: String,
    val taskDate: String,
)

fun TaskUI.fromDomain() = TaskModel(id, taskName, taskDate)