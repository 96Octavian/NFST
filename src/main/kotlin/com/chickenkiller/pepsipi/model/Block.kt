package com.chickenkiller.pepsipi.model

import java.time.LocalDateTime

data class Block(
    val Index: Int,
    val timestamp: LocalDateTime,
    val previousHash: String,
    val hash: String,
    val transactions: List<Transaction>
) {
}