package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.item.Item
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Bin
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Warehouse
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.WarehouseRabbitOutbound
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.PlacementContainerEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.toPlacedContainer
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.toPlacementContainerEmbeddable
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.toPlacementContainerEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.TransferItemByLocationsEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.toTransferItemByLocationsEmbeddable
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.toTransferItemByLocationsEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.toTransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.PlacementContainerJpaRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.TransferItemByLocationsJpaRepository

@Component
class CompositeWarehouseRepository (
    private val containerJpaRepository: PlacementContainerJpaRepository,
    private val transferItemByLocationsJpaRepository: TransferItemByLocationsJpaRepository,
    private val warehouseRabbitOutbound: WarehouseRabbitOutbound,
): WarehouseRepository {
    override fun findContainerByCode(code: String): Container? = warehouseRabbitOutbound.getContainerByCode(code)

    override fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination? = warehouseRabbitOutbound.findContainersByFilter(filter)


    override fun placeContainer(placementContainer: PlacementContainer): PlacedContainer =
        containerJpaRepository.save(
            PlacementContainerEntity(
                placementContainer = placementContainer.toPlacementContainerEmbeddable(),
                transaction = TransactionEntity(userLogin = placementContainer.userLogin)
            )
        ).toPlacedContainer().also {placedContainer ->
            warehouseRabbitOutbound.placeContainer(placedContainer)
        }

    @Transactional
    override fun findPlacedContainerByTransactionId(id: Int): PlacedContainer? =
        containerJpaRepository.findByTransactionId(id)?.toPlacedContainer()

    override fun updatePlacedContainer(placedContainer: PlacedContainer): PlacedContainer  =
        placedContainer.toPlacementContainerEntity().let {entity ->
            containerJpaRepository.save(entity)
        }.toPlacedContainer()

    override fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination? =
        warehouseRabbitOutbound.findInventoryBalanceByFilter(filter)

    override fun getItemDetail(itemCode: String): Item? = warehouseRabbitOutbound.getItemDetail(itemCode)

    override fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail? = warehouseRabbitOutbound.getLotDetail(filter)

    override fun transferItemByLocations(transferItemByLocations: TransferItemByLocations): TransferredItemByLocations =
        transferItemByLocationsJpaRepository.save(
            TransferItemByLocationsEntity(
                transferItemByLocations = transferItemByLocations.toTransferItemByLocationsEmbeddable(),
                transaction = TransactionEntity(userLogin = transferItemByLocations.userLogin),
            )
        ).toTransferredItemByLocations().also {transferredItemByLocations ->
            warehouseRabbitOutbound.transferItemByLocations(transferredItemByLocations)
        }

    @Transactional
    override fun findTransferredItemByLocationsByTransactionId(id: Int): TransferredItemByLocations? =
        transferItemByLocationsJpaRepository.findByTransactionId(id)?.toTransferredItemByLocations()


    override fun updateTransferredItemByLocations(transferredItemByLocations: TransferredItemByLocations): TransferredItemByLocations =
        transferredItemByLocations.toTransferItemByLocationsEntity().let {transferItemByLocationsEntity ->
            transferItemByLocationsJpaRepository.save(transferItemByLocationsEntity)
        }.toTransferredItemByLocations()

    override fun getWarehouseDetail(warehouseCode: String): Warehouse? = warehouseRabbitOutbound.getWarehouseDetail(warehouseCode)

    override fun getLocationDetailByFilter(filter: SearchLocationDetailShotFilter): Bin? =
        warehouseRabbitOutbound.getLocationDetailByFilter(filter)
}