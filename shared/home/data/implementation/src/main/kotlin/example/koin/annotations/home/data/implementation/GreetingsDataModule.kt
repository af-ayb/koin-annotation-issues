package example.koin.annotations.home.data.implementation

import example.koin.annotations.home.data.source.GreetingsDataSourceModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module(
    includes = [
        GreetingsDataSourceModule::class
    ]
)
@Configuration
@ComponentScan
class GreetingsDataModule