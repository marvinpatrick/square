package testBuilders

import coreDomain.shared.Employee
import directory.DirectoryRepoFake

class DirectoryRepoBuilder {

    private var employees = mutableSetOf<Employee>()

    fun withEmployee(employee: Employee): DirectoryRepoBuilder {
        employees.add(employee)
        return this
    }

    fun withNoEmployees(): DirectoryRepoBuilder {
        employees = mutableSetOf()
        return this
    }

    fun build(): DirectoryRepoFake {
        return DirectoryRepoFake(employees.toList())
    }
}