package example.koin.annotations.home.data.implementation

import example.koin.annotations.home.abstraction.GreetingsRepository
import example.koin.annotations.home.data.source.GreetingsDataSource
import example.koin.annotations.home.entity.Greeting
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
internal class GreetingsRepositoryImpl(
    val dataSource: GreetingsDataSource,
) : GreetingsRepository {

    override fun getGreetings(): Flow<List<Greeting>> {
        return dataSource.getGreetings()
    }
}