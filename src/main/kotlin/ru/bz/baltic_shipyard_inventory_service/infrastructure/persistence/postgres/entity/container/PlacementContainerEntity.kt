package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container

import jakarta.persistence.*
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionAbortReasonEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toAbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransaction
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransactionAbortReasonEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransactionEntity


@Entity
@Table(schema = "inventory_service", name = "placement_container")
class PlacementContainerEntity (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Embedded
    val placementContainer: PlacementContainerEmbeddable,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = EntityFields.TRANSACTION_ID)
    val transaction: TransactionEntity,

    @OneToOne( fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name= EntityFields.ABORT_REASON_ID)
    val transactionAbortReason: TransactionAbortReasonEntity? = null
)

fun PlacementContainerEntity.toPlacedContainer() = PlacedContainer(
    id = id,
    containerCode = placementContainer.containerCode,
    binCodeTarget = placementContainer.binCodeTarget,
    transaction = transaction.toTransaction(),
    abortReason = transactionAbortReason?.toAbortReason()
)

fun PlacedContainer.toPlacementContainerEntity() = PlacementContainerEntity(
    id = id,
    placementContainer = toPlacementContainerEmbeddable(),
    transaction = transaction.toTransactionEntity(),
    transactionAbortReason = abortReason?.toTransactionAbortReasonEntity()
)