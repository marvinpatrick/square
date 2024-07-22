package genericDomain.dependencyInjection

import coreDomain.directory.DirectoryAPI
import coreDomain.directory.DirectoryRepoImpl
import coreDomain.directory.DirectoryViewModel
import org.koin.dsl.module

val appModule = module {
    single { DirectoryViewModel(DirectoryRepoImpl(DirectoryAPI())) }
}