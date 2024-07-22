package testBuilders

import coreDomain.shared.Employee

class EmployeeBuilder {

    private var uuid = "some-uuid"
    private var fullName = "Eric Rogers"
    private var phoneNumber = "5556669870"
    private var emailAddress = "erogers.demo@squareup.com"
    private var biography = "A short biography describing the employee."
    private var photoUrlSmall = "https://some.url/path1.jpg"
    private var photoUrlLarge = "https://some.url/path2.jpg"
    private var team = "Seller"
    private var employeeType = "FULL_TIME"

    fun withFullName(fullName: String): EmployeeBuilder {
        this.fullName = fullName
        return this
    }

    fun build(): Employee {
        return Employee(
            uuid = uuid,
            fullName = fullName,
            emailAddress = emailAddress,
            team = team,
            employeeType = employeeType,
            biography = biography,
            phoneNumber = phoneNumber,
            photoUrlLarge = photoUrlLarge,
            photoUrlSmall = photoUrlSmall
        )
    }
}