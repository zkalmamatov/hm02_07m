package kg.less.hm02_07m

import android.app.Application
import kg.less.hm02_07m.data.database.di.dataModules
import kg.less.hm02_07m.presentation.module.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(dataModules)
            modules(dataModules)
            modules(uiModule)
        }
    }
}