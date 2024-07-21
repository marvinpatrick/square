import genericDomain.dependencyInjection.appModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

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

}