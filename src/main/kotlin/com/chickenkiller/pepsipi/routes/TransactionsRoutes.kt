package com.chickenkiller.pepsipi.routes

import com.chickenkiller.pepsipi.model.Transaction
import com.chickenkiller.pepsipi.model.database.DatabaseService.dbQuery
import com.chickenkiller.pepsipi.model.database.entities.TransactionEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private suspend fun getTransactions(offset: Long? = 0, limit: Int? = 10): List<Transaction> = dbQuery {
    TransactionEntity.all().limit(limit ?: 10, offset ?: 0).map { it.toTransaction() }
}

private suspend fun addTransaction(transaction: Transaction): Transaction = dbQuery {
    TransactionEntity.fromTransaction(transaction).toTransaction()
}

private suspend fun getTransaction(id: Int): Transaction? = dbQuery {
    TransactionEntity.findById(id)?.toTransaction()
}

fun Route.transactionsRoutes() {
    get("/transactions") {
        val offset = call.request.queryParameters["offset"]?.toLong()
        val limit = call.request.queryParameters["limit"]?.toInt()

        call.respond(getTransactions(offset, limit))
    }
    get("/transactions/{id}") {
        val id = call.parameters["id"]?.toInt() ?: -1
        call.respond(getTransaction(id) ?: HttpStatusCode.NoContent)
    }
    post("/transactions") {
        val transaction = call.receive<Transaction>()
        call.respond(addTransaction(transaction))
    }
}