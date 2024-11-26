package kg.less.hm02_07m.domain.di

import kg.less.hm02_07m.domain.usecase.GetAllNotesUseCase
import kg.less.hm02_07m.domain.usecase.GetTaskUseCase
import kg.less.hm02_07m.domain.usecase.InsertTaskUseCase
import kg.less.hm02_07m.domain.usecase.TaskDelete
import kg.less.hm02_07m.domain.usecase.UpdateTaskUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {

    factory { InsertTaskUseCase(get()) }
    single { UpdateTaskUseCase(get()) }
    single { GetAllNotesUseCase(get()) }
    single { TaskDelete(get()) }
    single { GetTaskUseCase(get()) }
}