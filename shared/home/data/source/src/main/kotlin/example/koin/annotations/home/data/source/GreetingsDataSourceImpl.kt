package example.koin.annotations.home.data.source

import example.koin.annotations.home.entity.Greeting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Single

@Single
class GreetingsDataSourceImpl() : GreetingsDataSource {
    override fun getGreetings(): Flow<List<Greeting>> {
        return flowOf(
            listOf(
                Greeting(phrase = "Hello", language = "English"),
                Greeting(phrase = "Hola", language = "Spanish"),
                Greeting(phrase = "Bonjour", language = "French"),
                Greeting(phrase = "Hallo", language = "German"),
                Greeting(phrase = "Ciao", language = "Italian"),
            )
        )
    }
}