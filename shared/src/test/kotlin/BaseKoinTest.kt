import genericDomain.dependencyInjection.appModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

abstract class BaseKoinTest : KoinTest {

    object Started {
        var started = false
    }

    init {
        if (!Started.started) {
            startKoin { loadKoinModules(appModule) }
            Started.started = true
        }
    }

    inline fun <reified T> testInject(): T {
        val clazz: T by inject()
        return clazz
    }
}