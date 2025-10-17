package example.koin.annotations.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinApp.startKoin {
            androidContext(this@App)
        }
    }
}

@KoinApplication
object KoinApp