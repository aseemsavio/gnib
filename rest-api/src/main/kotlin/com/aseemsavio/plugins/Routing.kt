package com.aseemsavio.plugins

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.io.File

fun Application.configureRouting(configMap: ConfigMap) {

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText(
                "You have reached gnib - Graal VM Native Image Builder. native Image Building has " +
                        "never been this fun before!"
            )
        }
        post("/native-image/arguments") {
            configMap[ArgumentsKey] = arguments(call.receiveText()).toArguments()
            println(configMap)
            call.respond(message = "Accepted", status = HttpStatusCode.Accepted)
        }
        post("/jar") {
            val file = call.receiveMultipart()
            file.forEachPart { part ->
                if (part is PartData.FileItem) {
                    val fileName = part.originalFileName!!
                    configMap[JarNameKey] = JarName(fileName)
                    val file = File("$fileName")
                    part.streamProvider().use { its ->
                        file.outputStream().buffered().use { its.copyTo(it) }
                    }
                    if (fileName.endsWith(".jar"))
                        call.respond(message = "Uploaded JAR", status = HttpStatusCode.Created)
                    else
                        call.respond(
                            message = "ERROR: You can upload only a single JAR file.",
                            status = HttpStatusCode.BadRequest
                        )
                }
                part.dispose()
            }
        }

        get("/native-image") {
            val image = configMap[JarNameKey]
            val imageName = if (image is JarName) image.value else ""
            val file = File("/images/$imageName")
            if (file.exists()) {
                call.respondFile(file)
            } else call.respond(message = "File Not available", status = HttpStatusCode.NotFound)
        }
    }
}
