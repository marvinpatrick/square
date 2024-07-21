package genericDomain.dependencyInjection

import coreDomain.directory.DirectoryAPI
import coreDomain.directory.DirectoryRepo
import coreDomain.directory.DirectoryViewModel
import org.koin.dsl.module

val appModule = module {
    single { DirectoryAPI() }
    single { DirectoryRepo(DirectoryAPI()) }
    single { DirectoryViewModel(DirectoryRepo(DirectoryAPI())) }
}