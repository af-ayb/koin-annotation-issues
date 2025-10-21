package example.koin.annotations.home.usecase

import example.koin.annotations.home.abstraction.GreetingsRepository
import example.koin.annotations.home.entity.Greeting
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetGreetingsUseCase(
    private val greetingsRepository: GreetingsRepository,
) {
    fun execute(): Flow<List<Greeting>> {
        return greetingsRepository.getGreetings()
    }
}