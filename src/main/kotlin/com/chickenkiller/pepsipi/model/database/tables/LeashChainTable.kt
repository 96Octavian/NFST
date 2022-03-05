package com.chickenkiller.pepsipi.model.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object LeashChainTable : IntIdTable("LeashChain") {
//    val index = integer("Index").autoIncrement("IndexSequence")
    val timestamp = timestamp("Timestamp")
    val previousHash = varchar("PreviousHash", 256)
    val hash = varchar("Hash", 256)
}