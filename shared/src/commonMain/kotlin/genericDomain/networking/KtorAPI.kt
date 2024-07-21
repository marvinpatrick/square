package genericDomain.networking

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

typealias ApiResponse = HttpResponse

abstract class KtorApi {

    private val baseUrl = "https://s3.amazonaws.com/sq-mobile-interview/"

    private val httpClient = HttpClient {
        install(Logging)
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Application.Json,
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun get(
        url: String,
        headers: List<Pair<String, String>> = emptyList(),
        vararg params: Pair<String, Any>
    ) = runCatching {
        httpClient.get {
            url("$baseUrl$url")
            contentType(ContentType.Application.Json)

            for (param in params) {
                parameter(key = param.first, value = param.second)
            }

            for (header in headers) {
                header(header.first, header.second)
            }

        }
    }
}