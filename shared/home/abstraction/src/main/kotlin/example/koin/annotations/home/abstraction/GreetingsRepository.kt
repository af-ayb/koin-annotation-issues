package example.koin.annotations.home.abstraction

import example.koin.annotations.home.entity.Greeting
import kotlinx.coroutines.flow.Flow

interface GreetingsRepository {
    fun getGreetings(): Flow<List<Greeting>>
}