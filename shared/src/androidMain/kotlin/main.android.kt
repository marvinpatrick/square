import androidx.compose.runtime.Composable

actual fun getDeviceType() = DeviceType.ANDROID

@Composable
fun MainView(isSufficientMemory: () -> Boolean) = App(isSufficientMemory)
