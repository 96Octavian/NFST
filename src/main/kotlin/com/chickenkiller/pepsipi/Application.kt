package com.chickenkiller.pepsipi

import com.chickenkiller.pepsipi.model.database.DatabaseService
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.chickenkiller.pepsipi.plugins.*

fun main() {

    DatabaseService.initDB();

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureSerialization()
    }.start(wait = true)
}
