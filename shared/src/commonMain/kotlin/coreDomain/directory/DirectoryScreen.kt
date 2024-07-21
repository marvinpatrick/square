package coreDomain.directory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coreDomain.shared.ScreenState
import genericDomain.dependencyInjection.KoinInjector
import getPlatformName

@Composable
fun DirectoryScreen() {
    val directoryViewModel: DirectoryViewModel = KoinInjector.inject()
    val screenState = directoryViewModel.state.collectAsState()
    directoryViewModel.getEmployees()
    when (screenState.value) {
        ScreenState.Loading -> {
            DirectoryLoadingLayout()
        }

        ScreenState.Ready -> {
            DirectoryScreenLayout()
        }

        ScreenState.Error -> {
            DirectoryErrorLayout()
        }
    }

}

@Composable
private fun DirectoryLoadingLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Loading")
    }
}

@Composable
private fun DirectoryScreenLayout() {
    var greetingText by remember { mutableStateOf("Hello, ${getPlatformName()}") }
    val directoryViewModel: DirectoryViewModel = KoinInjector.inject()
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            directoryViewModel.getEmployees()
            greetingText = "${directoryViewModel.employees.value?.size} employees"
        }) {
            Text(greetingText)
        }
    }
}

@Composable
private fun DirectoryErrorLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error")
    }
}
