package genericDomain.networking

import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode

val GOOD = HttpStatusCode.OK

abstract class KtorRepo {

    suspend inline fun <reified T> getResponse(httpResponse: ApiResponse): T {
        return httpResponse.call.body<T>()
    }

}
