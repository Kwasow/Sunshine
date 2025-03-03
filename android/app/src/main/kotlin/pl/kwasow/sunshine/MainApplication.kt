package pl.kwasow.sunshine

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.kwasow.sunshine.koin.managersModule
import pl.kwasow.sunshine.koin.viewModelsModule

class MainApplication : Application() {
    // ====== Interface methods
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(managersModule, viewModelsModule)
        }
    }
}
