package com.chickenkiller.pepsipi.model

import com.chickenkiller.pepsipi.model.database.entities.BlockEntity
import com.chickenkiller.pepsipi.model.database.entities.TransactionEntity
import kotlinx.serialization.*

@Serializable
data class Transaction(val sender: String, val recipient: String, val amount: Float) {
    fun toEntity(block: BlockEntity): TransactionEntity = TransactionEntity.new {
        sender = this.sender
        recipient = this.recipient
        amount = this.amount
        containerBlock = block
    }
}