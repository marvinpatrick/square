import androidx.compose.ui.window.ComposeUIViewController

actual fun getDeviceType() = DeviceType.IPHONE

fun MainViewController() = ComposeUIViewController { App(isSufficientMemory = { true }) }