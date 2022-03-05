package com.chickenkiller.pepsipi.model

import kotlinx.serialization.*

@Serializable
data class Block(
//    val index: Int = 0,
    val timestamp: String,
    val previousHash: String,
    val hash: String,
    val transactions: List<Transaction> = emptyList()
)