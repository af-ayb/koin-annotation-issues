package example.koin.annotations

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object KoinInjectHelper : KoinComponent {

    fun initKoin() {
        startKoin {
        }
    }

    val getPostsUseCase: GreetingsUseCase by inject()
}