package genericDomain.networking.model

import businessDomain.directory.Employee

data class EmployeeDTO(
    val biography: String? = null,
    val email_address: String? = null,
    val employee_type: String? = null,
    val full_name: String? = null,
    val phone_number: String? = null,
    val photo_url_large: String? = null,
    val photo_url_small: String? = null,
    val team: String? = null,
    val uuid: String? = null
) {

    fun toEmployee(): Employee? {
        return if (
            this.uuid?.isNotBlank() == true &&
            this.full_name?.isNotBlank() == true &&
            this.email_address?.isNotBlank() == true &&
            this.team?.isNotBlank() == true &&
            this.employee_type?.isNotBlank() == true
        ) {
            Employee(
                uuid = uuid,
                fullName = full_name,
                emailAddress = email_address,
                team = team,
                employeeType = employee_type,
                biography = biography,
                phoneNumber = phone_number,
                photoUrlLarge = photo_url_large,
                photoUrlSmall = photo_url_small
            )
        } else {
            null
        }
    }

}