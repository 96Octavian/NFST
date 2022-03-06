package com.chickenkiller.pepsipi.model.database.entities

import com.chickenkiller.pepsipi.model.Block
import com.chickenkiller.pepsipi.model.database.tables.LeashChainTable
import com.chickenkiller.pepsipi.model.database.tables.TransactionsTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.time.Instant

class BlockEntity(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, BlockEntity>(LeashChainTable) {
        fun fromBlock(block: Block): BlockEntity = BlockEntity.new {
            //index = block.index
            timestamp = Instant.parse(block.timestamp)
            previousHash = block.previousHash
            hash = block.hash
        }
    }

    //    var index by LeashChainTable.index
    var timestamp by LeashChainTable.timestamp
    var previousHash by LeashChainTable.previousHash
    var hash by LeashChainTable.hash
    val transactions by TransactionEntity optionalReferrersOn TransactionsTable.containerBlock

    fun toBlock(): Block =
        Block(/*index, */timestamp.toString(), previousHash, hash, transactions.map { it.toTransaction() })
}