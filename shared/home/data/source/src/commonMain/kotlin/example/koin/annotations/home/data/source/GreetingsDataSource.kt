package example.koin.annotations.home.data.source

import example.koin.annotations.home.entity.Greeting
import kotlinx.coroutines.flow.Flow

interface GreetingsDataSource {
    fun getGreetings(): Flow<List<Greeting>>
}