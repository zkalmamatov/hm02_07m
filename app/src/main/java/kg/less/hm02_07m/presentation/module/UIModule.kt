package kg.less.hm02_07m.presentation.module

import kg.less.hm02_07m.presentation.viewmodel.MainActivityViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModule: Module = module {
    factory { MainActivityViewModel(get(), get()) }
}