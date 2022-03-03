package com.chickenkiller.pepsipi.model.database

import com.chickenkiller.pepsipi.model.database.tables.LeashChainTable
import com.chickenkiller.pepsipi.model.database.tables.TransactionsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseService {
    /*val db by lazy {
        Database.connect(
            "jdbc:postgresql://localhost:5432/nfst", driver = "org.postgresql.Driver",
            user = "nfst", password = "nfst"
        )
    }*/

    fun initDB() {
        Database.connect(
            "jdbc:postgresql://localhost:5432/nfst", driver = "org.postgresql.Driver", user = "nfst", password = "nfst"
        )

        transaction {
            SchemaUtils.createMissingTablesAndColumns(LeashChainTable, TransactionsTable)
        }
    }

    private suspend fun <T> dbQuery(block: () -> T): T {
        return withContext(Dispatchers.IO) {
            transaction { block() }
        }
    }
}