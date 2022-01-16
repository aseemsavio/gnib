package com.aseemsavio

import com.aseemsavio.plugins.configMap
import com.aseemsavio.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {

    val configMap = configMap()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting(configMap)
    }.start(wait = true)
}
