package example.koin.annotations.app

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

/*
    Root module including other modules
    included modules are commented out to avoid:
    [ksp] java.lang.IllegalStateException: can't find module metadata for 'example.koin.annotations.home.data.source.GreetingsDataSourceModule'
        in current modules or any meta tags

    If it is commented out, the app will run, but the dependencies in those modules won't be checked,
        which can cause runtime errors.
*/
@Module(
//    includes = [
//        GreetingsDataModule::class,
//        HomeUiModule::class
//    ]
)
@Configuration
@ComponentScan
class AppModule