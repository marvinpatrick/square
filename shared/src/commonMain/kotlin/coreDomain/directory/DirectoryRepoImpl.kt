package coreDomain.directory

import coreDomain.shared.DirectoryRepo
import coreDomain.shared.Employee
import genericDomain.networking.GOOD
import genericDomain.networking.KtorRepo
import genericDomain.networking.model.EmployeeDirectoryDTO

class DirectoryRepoImpl(private val directoryAPI: DirectoryAPI) : DirectoryRepo, KtorRepo() {

    override suspend fun getEmployees(): List<Employee>? {
        try {
            directoryAPI.getEmployees().getOrNull()?.let { apiResponse ->
                if (apiResponse.status == GOOD) {
                    return getResponse<EmployeeDirectoryDTO>(apiResponse).employees.map {
                        it.toEmployee() ?: return emptyList()
                    }
                }
            }
            return null
        } catch (e: Exception) {
            return null
        }
    }
}