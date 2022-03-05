package com.chickenkiller.pepsipi.routes

import com.chickenkiller.pepsipi.model.Block
import com.chickenkiller.pepsipi.model.database.DatabaseService.dbQuery
import com.chickenkiller.pepsipi.model.database.entities.BlockEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.Instant

fun Application.blocksRoutes() {
    routing {
        blocks()
    }
}

private suspend fun getBlocks(offset: Long = 0, limit: Int = 10): List<Block> = dbQuery {
    BlockEntity.all().limit(limit, offset).map { it.toBlock() }
}

private suspend fun addBlock(block: Block): Block {
    return dbQuery {
        BlockEntity.new {
            //index = block.index
            timestamp = Instant.parse(block.timestamp)
            previousHash = block.previousHash
            hash = block.hash
        }.also { be ->
            block.transactions.forEach {
                it.toEntity(be)
            }
        }
    }.toBlock()
}

fun Route.blocks() {
    get("/blocks") {
        call.request.queryParameters["offset"]?.toLong()?.let { offset ->
            call.request.queryParameters["limit"]?.toInt()?.let { limit -> getBlocks(offset, limit) }
        }?.let { response ->
            call.respond(
                response
            )
        }
    }
    post("/blocks") {
        val block = call.receive<Block>()
        if (block.transactions.isEmpty()) call.respond(HttpStatusCode.BadRequest)
        else call.respond(addBlock(block))
        //call.respond(block)
    }
}