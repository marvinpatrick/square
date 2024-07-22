import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(isSufficientMemory: () -> Boolean) = App(isSufficientMemory)
