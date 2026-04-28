package ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.transfer

import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import java.time.LocalDateTime

data class TransactionTransferredItemByLocations(
    override val id: Int,
    override val created: LocalDateTime,
    override val status: TransactionStatus,
    override val userLogin: String,
    val transfer: TransferredItemByLocations
): TransactionBase
