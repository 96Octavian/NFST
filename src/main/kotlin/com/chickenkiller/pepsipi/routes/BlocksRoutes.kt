package com.chickenkiller.pepsipi.routes

import com.chickenkiller.pepsipi.model.Block
import com.chickenkiller.pepsipi.model.database.DatabaseService.dbQuery
import com.chickenkiller.pepsipi.model.database.entities.BlockEntity
import com.chickenkiller.pepsipi.model.database.entities.TransactionEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private suspend fun getBlocks(offset: Long? = 0, limit: Int? = 10): List<Block> = dbQuery {
    BlockEntity.all().limit(limit ?: 10, offset ?: 0).map { it.toBlock() }
}

// TODO: Check block has at least one transaction
// TODO: Check each transaction exists and is not already mined
// TODO: Verify block
private suspend fun addBlock(block: Block): Block = dbQuery {
    BlockEntity.fromBlock(block).also { be ->
        block.transactions.forEach {
            TransactionEntity.fromTransaction(it, be)
        }
    }.toBlock()
}

private suspend fun getBlock(id: Int): Block? = dbQuery {
    BlockEntity.findById(id)?.toBlock()
}

fun Route.blocksRoutes() {
    get("/blocks") {
        val offset = call.request.queryParameters["offset"]?.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt()

        call.respond(getBlocks(offset, limit))
    }
    get("/blocks/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1
        call.respond(getBlock(id) ?: HttpStatusCode.NoContent)
    }

    post("/blocks") {
        val block = call.receive<Block>()
        if (block.transactions.isEmpty()) call.respond(HttpStatusCode.BadRequest) // TODO: Move this check inside addBlock()
        else call.respond(addBlock(block))
    }
}