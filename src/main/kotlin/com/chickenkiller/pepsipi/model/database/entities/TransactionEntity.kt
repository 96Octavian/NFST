package com.chickenkiller.pepsipi.model.database.entities

import com.chickenkiller.pepsipi.model.Transaction
import com.chickenkiller.pepsipi.model.database.tables.TransactionsTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TransactionEntity(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, TransactionEntity>(TransactionsTable) {
        fun fromTransaction(transaction: Transaction, blockEntity: BlockEntity? = null): TransactionEntity =
            TransactionEntity.new {
                sender = transaction.sender
                recipient = transaction.recipient
                amount = transaction.amount
                containerBlock = blockEntity
            }
    }

    var sender by TransactionsTable.sender
    var recipient by TransactionsTable.recipient
    var amount by TransactionsTable.amount
    var containerBlock by BlockEntity optionalReferencedOn TransactionsTable.containerBlock

    fun toTransaction(): Transaction = Transaction(sender, recipient, amount, containerBlock?.id?.value)
}