package coreDomain.directory

import coreDomain.shared.Employee

interface DirectoryRepo {
    suspend fun getEmployees(): List<Employee>?
}