package com.chickenkiller.pepsipi.model.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object LeashChainTable : IntIdTable("LeashChain") {
    val index = integer("Index").autoIncrement()
    val timestamp = datetime("Timestamp")
    val previousHash = varchar("PreviousHash", 256)
    val hash = varchar("hash", 256)
}