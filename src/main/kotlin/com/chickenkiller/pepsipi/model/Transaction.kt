package com.chickenkiller.pepsipi.model

import kotlinx.serialization.*

@Serializable
data class Transaction(val sender: String, val recipient: String, val amount: Float, val blockIndex: Int? = -1)