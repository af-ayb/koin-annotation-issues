package example.koin.annotations.home

import example.koin.annotations.home.usecase.GreetingsUseCaseModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module(
    includes = [
        GreetingsUseCaseModule::class,
    ]
)
@Configuration
@ComponentScan
class HomeUiModule