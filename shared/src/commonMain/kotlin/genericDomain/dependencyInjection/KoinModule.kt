package genericDomain.dependencyInjection

import businessDomain.directory.DirectoryAPI
import businessDomain.directory.DirectoryRepo
import businessDomain.directory.DirectoryViewModel
import org.koin.dsl.module

val appModule = module {
    single { DirectoryViewModel(DirectoryRepo(DirectoryAPI())) }
}