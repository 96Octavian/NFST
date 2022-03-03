package com.chickenkiller.pepsipi

import com.chickenkiller.pepsipi.model.database.DatabaseService

fun main(args: Array<String>) {
    DatabaseService.initDB()
    io.ktor.server.netty.EngineMain.main(args)
}
