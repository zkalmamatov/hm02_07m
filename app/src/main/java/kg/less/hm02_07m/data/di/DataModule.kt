package kg.less.hm02_07m.data.di

import androidx.room.Room
import kg.less.hm02_07m.data.database.AppDatabase
import kg.less.hm02_07m.data.repositorylmpl.TaskManagerRepositoryImpl
import kg.less.hm02_07m.domain.repository.TaskManagerRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModules:Module = module {

    single {

        Room.databaseBuilder(get(), AppDatabase::class.java, "TaskDataBase")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get <AppDatabase>().taskManagerDao() }

    single <TaskManagerRepository>{ TaskManagerRepositoryImpl(get()) }
}