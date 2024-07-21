package genericDomain.dependencyInjection

import androidx.compose.runtime.Composable
import org.koin.compose.koinInject

object KoinInjector {

    @Composable
    inline fun <reified T> inject(): T {
        return koinInject<T>()
    }

}