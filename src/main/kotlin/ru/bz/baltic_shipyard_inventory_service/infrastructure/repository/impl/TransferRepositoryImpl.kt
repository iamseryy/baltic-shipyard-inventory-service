package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.TransferItemByLocationsEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.toTransferItemByLocationsEmbeddable
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.toTransferItemByLocationsEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.toTransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.TransferItemByLocationsJpaRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.TransferRepository


@Service
class TransferRepositoryImpl(
    private val transferItemByLocationsJpaRepository: TransferItemByLocationsJpaRepository,
    private val rabbitRepository: RabbitRepository
): TransferRepository {
    override fun transferItemByLocations(transferItemByLocations: TransferItemByLocations): TransferredItemByLocations =
        transferItemByLocationsJpaRepository.save(
            TransferItemByLocationsEntity(
                transferItemByLocations = transferItemByLocations.toTransferItemByLocationsEmbeddable(),
                transaction = TransactionEntity(userLogin = transferItemByLocations.userLogin),
            )
        ).toTransferredItemByLocations().also {transferredItemByLocations ->
            rabbitRepository.transferItemByLocations(transferredItemByLocations)
        }

    override fun findTransferredItemByLocationsByTransactionId(id: Int): TransferredItemByLocations? =
        transferItemByLocationsJpaRepository.findByTransactionId(id)?.toTransferredItemByLocations()


    override fun updateTransferredItemByLocations(transferredItemByLocations: TransferredItemByLocations): TransferredItemByLocations =
        transferredItemByLocations.toTransferItemByLocationsEntity().let {transferItemByLocationsEntity ->
            transferItemByLocationsJpaRepository.save(transferItemByLocationsEntity)
        }.toTransferredItemByLocations()
}