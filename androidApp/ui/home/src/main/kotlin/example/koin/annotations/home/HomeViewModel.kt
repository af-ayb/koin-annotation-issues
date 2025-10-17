package example.koin.annotations.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import example.koin.annotations.home.usecase.GetGreetingsUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    getGreetingsUseCase: GetGreetingsUseCase,
) : ViewModel() {

    val greetings = getGreetingsUseCase.execute()
        .map {
            it
        }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.Lazily,
            initialValue = emptyList()
        )
}