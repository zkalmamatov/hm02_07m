package kg.less.hm02_07m.domain.di

import kg.less.hm02_07m.domain.usecase.InsertTaskUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {
    factory { InsertTaskUseCase(get()) }
}