package coreDomain.directory

import genericDomain.networking.ApiResponse
import genericDomain.networking.KtorApi

class DirectoryAPI : KtorApi() {

    suspend fun getEmployees(): Result<ApiResponse> {
        return get("employees_malformed.json")
    }

}
