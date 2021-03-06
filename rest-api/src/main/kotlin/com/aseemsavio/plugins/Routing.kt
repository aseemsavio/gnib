package com.aseemsavio.plugins

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.coroutines.delay
import java.io.File

fun Application.configureRouting(configMap: ConfigMap) {

    routing {
        get("/") {
            call.respondText(
                "You have reached gnib - Graal VM Native Image Builder. native Image Building has " +
                        "never been this fun before!"
            )
        }
        post("/native-image/arguments") {
            try {
                configMap[ArgumentsKey] = arguments(call.receiveText()).toArguments()
                println("Arguments changed: ${configMap[ArgumentsKey]}")
                call.respond(message = "Accepted", status = HttpStatusCode.Accepted)
            } catch (e: Exception) {
                call.respond(message = e.message!!, status = HttpStatusCode.BadRequest)
            }
        }
        post("/jar") {
            try {
                val file = call.receiveMultipart()
                file.forEachPart { part ->
                    if (part is PartData.FileItem) {
                        val fileName = part.originalFileName!!
                        configMap[JarNameKey] = JarName(fileName)
                        val file = File("$fileName")
                        part.streamProvider().use { its ->
                            file.outputStream().buffered().use { its.copyTo(it) }
                        }
                        println("JAR uploaded: ${file.absolutePath}")
                        call.respond(message = "Uploaded JAR", status = HttpStatusCode.Created)
                    }
                    part.dispose()
                }
            } catch (e: Exception) {
                call.respond(message = e.message!!, status = HttpStatusCode.BadRequest)
            }
        }

        post("/build/native-image") {
            delay(10000)
            call.respond(message = "Build Successful!", status = HttpStatusCode.Created)
        }

        get("/native-image") {
            val image = configMap[JarNameKey]
            val imageName = if (image is JarName) image.value else ""
            val file = File("/images/$imageName")
            if (file.exists()) {
                call.respondFile(file)
            } else call.respond(message = "File Not available", status = HttpStatusCode.NotFound)
        }

        get("/logs") {
            call.respond(message = "Logs will be sent.")
        }
    }
}
