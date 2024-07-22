package coreDomain.shared

import coreDomain.shared.Employee

interface DirectoryRepo {
    suspend fun getEmployees(): List<Employee>?
}