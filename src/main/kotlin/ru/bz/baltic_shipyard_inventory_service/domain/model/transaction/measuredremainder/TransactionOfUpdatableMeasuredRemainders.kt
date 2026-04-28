package ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import java.time.LocalDateTime


data class TransactionOfUpdatableMeasuredRemainders(
    override val id: Int,
    override val created: LocalDateTime,
    override val status: TransactionStatus,
    override val userLogin: String,
    val measuredRemainders: List<UpdatableMeasuredRemainder>,
): TransactionBase
