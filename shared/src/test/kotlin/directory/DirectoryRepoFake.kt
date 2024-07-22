package directory

import coreDomain.shared.DirectoryRepo
import coreDomain.shared.Employee

class DirectoryRepoFake(private val employees: List<Employee>) : DirectoryRepo {

    override suspend fun getEmployees(): List<Employee> {
        return employees
    }

}