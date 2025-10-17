package example.koin.annotations.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import example.koin.annotations.home.entity.Greeting
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val greetings by viewModel.greetings.collectAsStateWithLifecycle()
    HomeScreen(greetings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    greetings: List<Greeting>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home") },
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        ) {
            items(greetings) { greeting ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.secondaryContainer,
                        contentColor = colorScheme.onSurface,
                    )

                ) {
                    Text(
                        text = greeting.phrase,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "From: ${greeting.language}",
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            greetings = listOf(
                Greeting(phrase = "Hello", language = "English"),
                Greeting(phrase = "Hola", language = "Spanish"),
                Greeting(phrase = "Bonjour", language = "French"),
            )
        )
    }
}