package com.chickenkiller.pepsipi.model.database.entities

import com.chickenkiller.pepsipi.model.Block
import com.chickenkiller.pepsipi.model.database.tables.LeashChainTable
import com.chickenkiller.pepsipi.model.database.tables.TransactionsTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BlockEntity(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, BlockEntity>(LeashChainTable)

//    var index by LeashChainTable.index
    var timestamp by LeashChainTable.timestamp
    var previousHash by LeashChainTable.previousHash
    var hash by LeashChainTable.hash
    val transactions  by TransactionEntity referrersOn TransactionsTable.containerBlock

    fun toBlock(): Block = Block(/*index, */timestamp.toString(), previousHash, hash, transactions.map { it.transaction })
}