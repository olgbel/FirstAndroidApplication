package com.example.firstandroidapplication.client

import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.ContentType

object Api {
    const val url = "https://raw.githubusercontent.com/olgbel/GSONtoJSON/master/posts.json"
    const val advertisingURL = "https://raw.githubusercontent.com/olgbel/GSONtoJSON/master/advertising.json"

    val client = HttpClient {
        install(JsonFeature) {
            acceptContentTypes = listOf(
                ContentType.Text.Plain,
                ContentType.Application.Json
            )
            serializer = GsonSerializer()
        }
    }
}