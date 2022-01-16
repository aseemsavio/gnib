package com.aseemsavio.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureRouting(configMap: ConfigMap) {

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("You have reached gnib - Graal VM Native Image Builder. native Image Building has " +
                    "never been this fun before!")
        }
        post("/native-image/arguments") {
            configMap[ArgumentsKey] = arguments(call.receiveText()).toArguments()
            println(configMap)
            call.respond(message = "Accepted", status = HttpStatusCode.Accepted)
        }
    }
}
