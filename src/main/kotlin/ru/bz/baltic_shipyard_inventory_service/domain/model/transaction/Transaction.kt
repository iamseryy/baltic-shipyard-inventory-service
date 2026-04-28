package ru.bz.baltic_shipyard_inventory_service.domain.model.transaction

import java.time.LocalDateTime


data class Transaction (
    override val id: Int,
    override val created: LocalDateTime,
    override val status: TransactionStatus,
    override val userLogin: String
): TransactionBase