package ru.bz.baltic_shipyard_inventory_service.domain.model.transaction

import java.time.LocalDateTime


interface TransactionBase {
    val id: Int
    val created: LocalDateTime
    val status: TransactionStatus
    val userLogin: String
}