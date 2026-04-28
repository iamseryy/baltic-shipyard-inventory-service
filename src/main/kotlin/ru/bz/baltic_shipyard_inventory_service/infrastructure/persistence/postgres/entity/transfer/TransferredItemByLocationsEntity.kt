package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer

import jakarta.persistence.*
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionAbortReasonEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toAbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransaction
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransactionAbortReasonEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransactionEntity


@Entity
@Table(schema = "inventory_service", name = "transfer_by_locations")
class TransferItemByLocationsEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Embedded
    val transferItemByLocations: TransferItemByLocationsEmbeddable,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = EntityFields.TRANSACTION_ID)
    val transaction: TransactionEntity,

    @OneToOne( fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name= EntityFields.ABORT_REASON_ID)
    val transactionAbortReason: TransactionAbortReasonEntity? = null
)

fun TransferItemByLocationsEntity.toTransferredItemByLocations() = TransferredItemByLocations(
    id = id,
    warehouseCode = transferItemByLocations.warehouseCode,
    binCodeSource = transferItemByLocations.binCodeSource,
    binCodeTarget = transferItemByLocations.binCodeTarget,
    itemCode = transferItemByLocations.itemCode,
    lotCode = transferItemByLocations.lotCode,
    quantity = transferItemByLocations.quantity,
    transaction = transaction.toTransaction(),
    abortReason = transactionAbortReason?.toAbortReason()
)

fun TransferredItemByLocations.toTransferItemByLocationsEntity() = TransferItemByLocationsEntity(
    id = id,
    transferItemByLocations = toTransferItemByLocationsEmbeddable(),
    transaction = transaction.toTransactionEntity(),
    transactionAbortReason = abortReason?.toTransactionAbortReasonEntity()
)