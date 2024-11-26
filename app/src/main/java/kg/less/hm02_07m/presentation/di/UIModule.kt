package kg.less.hm02_07m.presentation.di

import kg.less.hm02_07m.domain.usecase.GetAllNotesUseCase
import kg.less.hm02_07m.domain.usecase.GetTaskUseCase
import kg.less.hm02_07m.domain.usecase.InsertTaskUseCase
import kg.less.hm02_07m.domain.usecase.TaskDelete
import kg.less.hm02_07m.domain.usecase.UpdateTaskUseCase
import kg.less.hm02_07m.presentation.fragments.taskList.TaskViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModule: Module = module {

    factory { TaskViewModel(get(), get(), get(), get()) }
    single { InsertTaskUseCase(get()) }
    single { GetAllNotesUseCase(get()) }
    single { UpdateTaskUseCase(get()) }
    single { TaskDelete(get()) }
    single { GetTaskUseCase(get()) }
}