package com.chickenkiller.pepsipi.model.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object TransactionsTable : IntIdTable("Transactions") {
    val sender = text("Sender")
    val recipient = text("Recipient")
    val amount = float("Amount")
    val containerBlock = reference("ContainerBlock", LeashChainTable)
}