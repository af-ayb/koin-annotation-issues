package example.koin.annotations.app

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import example.koin.annotations.home.HomeGraph
import example.koin.annotations.home.homeGraph

@Composable
internal fun AppContent() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeGraph,
            modifier = Modifier
                .padding(contentPadding)
                .consumeWindowInsets(contentPadding),
            builder = { buildAppNavGraph() },
        )
    }
}

private fun NavGraphBuilder.buildAppNavGraph() {
    homeGraph()
}