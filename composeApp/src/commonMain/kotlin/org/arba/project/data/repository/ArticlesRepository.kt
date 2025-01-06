package org.arba.project.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import org.arba.project.data.network.ClientHttp

class ArticlesRepository(val clientHttp: ClientHttp) {
    suspend fun getArticlesApi(): HttpResponse {
        return clientHttp.httpClient.get {
            url("top-headlines")
            parameter("country", "us")
            parameter("apiKey", "e2f837df5a78451ea447c78f6b48cf83")
        }
    }

    suspend fun searchArticleApi(inputTextSearch: String): HttpResponse {
        return clientHttp.httpClient.get {
            url("everything")
            parameter("q", inputTextSearch)
            parameter("apiKey", "e2f837df5a78451ea447c78f6b48cf83")
        }
    }
}