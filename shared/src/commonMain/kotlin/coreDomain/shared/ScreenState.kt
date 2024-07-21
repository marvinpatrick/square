package coreDomain.shared

sealed class ScreenState {

    data object Loading : ScreenState()
    data object Ready : ScreenState()
    data object Error : ScreenState()

}