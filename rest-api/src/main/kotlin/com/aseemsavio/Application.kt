package com.aseemsavio

import com.aseemsavio.plugins.configMap
import com.aseemsavio.plugins.configureRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*

fun main() {
    val configMap = configMap()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            header(HttpHeaders.AccessControlAllowOrigin)
            allowNonSimpleContentTypes = true
            allowSameOrigin = true
            anyHost()
            println("CORS enabled")
        }
        configureRouting(configMap)
    }.start(wait = true)
}
