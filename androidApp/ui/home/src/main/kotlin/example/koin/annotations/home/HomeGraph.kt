package example.koin.annotations.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraph

fun NavGraphBuilder.homeGraph() {
    navigation<HomeGraph>(startDestination = HomeRoute) {
        homeScreen()
    }
}