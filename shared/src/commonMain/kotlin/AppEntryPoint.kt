import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import coreDomain.directory.DirectoryScreen
import genericDomain.dependencyInjection.appModule
import org.koin.compose.KoinApplication

@Composable
fun App(isSufficientMemory: () -> Boolean) {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme { DirectoryScreen(isSufficientMemory) }
    }
}

expect fun getPlatformName(): String