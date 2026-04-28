package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.measuredremainder

import jakarta.persistence.*
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.*


@Entity
@Table(schema = "inventory_service", name = "updatable_measuredremainder")
class UpdatableMeasuredRemainderEntity (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Embedded
    val measuredRemainder: MeasuredRemainderEmbeddable,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = EntityFields.TRANSACTION_ID)
    val transaction: TransactionEntity,

    @OneToOne( fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name= EntityFields.ABORT_REASON_ID)
    val transactionAbortReason: TransactionAbortReasonEntity? = null
)

fun UpdatableMeasuredRemainder.toUpdatableMeasuredRemainderEntity() = UpdatableMeasuredRemainderEntity(
    id = id,
    measuredRemainder = toMeasuredRemainderEmbeddable(),
    transaction = transaction.toTransactionEntity(),
    transactionAbortReason = abortReason?.toTransactionAbortReasonEntity()
)

fun UpdatableMeasuredRemainderEntity.toUpdatableMeasuredRemainder() = UpdatableMeasuredRemainder(
    id = id,
    measuredRemainderId = measuredRemainder.measuredRemainderId,
    remainder = measuredRemainder.remainder,
    project = measuredRemainder.project,
    material = measuredRemainder.material,
    warehouse = measuredRemainder.warehouse,
    location = measuredRemainder.location,
    sequence = measuredRemainder.sequence,
    status = measuredRemainder.status,
    comment = measuredRemainder.comment,
    length = measuredRemainder.length,
    width = measuredRemainder.width,
    depth = measuredRemainder.depth,
    transaction = transaction.toTransaction(),
    abortReason = transactionAbortReason?.toAbortReason()
)

fun UpdatableMeasuredRemainderEntity.toInventoriedMeasuredRemainder() = InventoriedMeasuredRemainder(
    id = id,
    measuredRemainderId = measuredRemainder.measuredRemainderId,
    remainder = measuredRemainder.remainder,
    project = measuredRemainder.project,
    material = measuredRemainder.material,
    warehouse = measuredRemainder.warehouse,
    location = measuredRemainder.location,
    sequence = measuredRemainder.sequence,
    status = measuredRemainder.status,
    comment = measuredRemainder.comment,
    length = measuredRemainder.length,
    width = measuredRemainder.width,
    depth = measuredRemainder.depth,
    abortReason = transactionAbortReason?.toAbortReason()
)

fun InventoriedMeasuredRemainder.toUpdatableMeasuredRemainderEntity(transactionEntity: TransactionEntity) =
    UpdatableMeasuredRemainderEntity(
        id = id,
        measuredRemainder = toMeasuredRemainderEmbeddable(),
        transaction = transactionEntity,
        transactionAbortReason = abortReason?.toTransactionAbortReasonEntity()
    )

fun InventoriedMeasuredRemainder.toMeasuredRemainderEmbeddable() = MeasuredRemainderEmbeddable(
    measuredRemainderId = measuredRemainderId,
    remainder = remainder,
    project = project,
    material = material,
    warehouse = warehouse,
    location = location,
    sequence = sequence,
    status = status,
    comment = comment,
    length = length,
    width = width,
    depth = depth,
)