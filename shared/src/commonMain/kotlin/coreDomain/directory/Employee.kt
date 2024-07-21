package coreDomain.directory

class Employee(
    val uuid: String,
    val fullName: String,
    val emailAddress: String,
    val team: String,
    val employeeType: String,
    val biography: String? = null,
    val phoneNumber: String? = null,
    val photoUrlLarge: String? = null,
    val photoUrlSmall: String? = null,
) {

}