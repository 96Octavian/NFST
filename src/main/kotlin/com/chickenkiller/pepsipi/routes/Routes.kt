package com.chickenkiller.pepsipi.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*

@Suppress("unused") // Referenced in application.conf
fun Application.routes() {
    routing {
        blocksRoutes()
        transactionsRoutes()
    }
}
