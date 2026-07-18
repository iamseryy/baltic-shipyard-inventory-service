package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.impl

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.item.Item
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Bin
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Warehouse
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitKeys
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitProperties
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.ContainerMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.ContainersPaginationMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.PlacementContainerMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.toContainer
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.toContainersPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.toPlacementContainerTransactionDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.filter.toSearchContainerFilterDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.filter.toSearchInventoryBalanceFilterDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.filter.toSearchLocationDetailShotFilterDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.filter.toSearchLotDetailShotFilterDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.item.ItemMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.item.toItem
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.lot.LotDetailMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.lot.toLotDetail
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.stock.StockListPaginationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.stock.toStockListPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.transaction.transfer.TransferItemByBinTransactionMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.transaction.transfer.toTransferItemByLocationsTransactionDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.location.BinMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.location.WarehouseMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.location.toBin
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.location.toWarehouse
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.WarehouseRabbitOutbound


@Component
class WarehouseRabbitOutboundImpl(
    private val amqpTemplate: AmqpTemplate,
    private val props: RabbitProperties
): WarehouseRabbitOutbound {
    override fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination? =
        with (
            filter.toSearchInventoryBalanceFilterDto()
        ) {
            amqpTemplate.convertSendAndReceiveAsType(
                props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
                props.getKey(RabbitKeys.FIND_STOCK_LIST_KEY),
                this,
                object: ParameterizedTypeReference<StockListPaginationDto>() {}
            )
        }?.toStockListPagination()

    override fun getItemDetail(itemCode: String): Item? =
        amqpTemplate.convertSendAndReceiveAsType(
            props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
            props.getKey(RabbitKeys.GET_ITEM_DETAIL_KEY),
            itemCode,
            object: ParameterizedTypeReference<ItemMessageDto>() {}
        )?.let {itemMessageDto ->
            itemMessageDto.item?.toItem()
        }

    override fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail? =
        with(
            filter.toSearchLotDetailShotFilterDto()
        ){
            amqpTemplate.convertSendAndReceiveAsType(
                props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
                props.getKey(RabbitKeys.GET_LOT_DETAIL_KEY),
                this,
                object: ParameterizedTypeReference<LotDetailMessageDto>() {}
            )?.let { lotDetailMessageDto ->
                lotDetailMessageDto.lot?.toLotDetail()
            }
        }

    override fun getWarehouseDetail(warehouseCode: String): Warehouse? =
        amqpTemplate.convertSendAndReceiveAsType(
            props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
            props.getKey(RabbitKeys.GET_WAREHOUSE_DETAIL_KEY),
            warehouseCode,
            object: ParameterizedTypeReference<WarehouseMessageDto>() {}
        )?.let {warehouseMessageDto ->
            warehouseMessageDto.warehouse?.toWarehouse()
        }

    override fun getLocationDetailByFilter(filter: SearchLocationDetailShotFilter): Bin? =
        with(filter.toSearchLocationDetailShotFilterDto()) {
            amqpTemplate.convertSendAndReceiveAsType(
                props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
                props.getKey(RabbitKeys.GET_LOCATION_DETAIL_KEY),
                this,
                object: ParameterizedTypeReference<BinMessageDto>() {}
            )?.let {warehouseMessageDto ->
                warehouseMessageDto.bin?.toBin()
            }
        }

    override fun transferItemByLocations(transferredItemByLocations: TransferredItemByLocations) {
        transferredItemByLocations.toTransferItemByLocationsTransactionDto().let { transferTransaction ->
            amqpTemplate.convertAndSend(
                props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
                props.getKey(RabbitKeys.TRANSFER_BY_BINS_KEY),
                TransferItemByBinTransactionMessage(transferTransaction)
            )
        }
    }

    override fun getContainerByCode(code: String): Container? =
        amqpTemplate.convertSendAndReceiveAsType(
            props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
            props.getKey(RabbitKeys.GET_CONTAINER_KEY),
            code,
            object: ParameterizedTypeReference<ContainerMessageDto>() {}
        )?.let {containerDetailMessage ->
            containerDetailMessage.container?.toContainer()
        }

    override fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination? =
        with(filter.toSearchContainerFilterDto()) {
            amqpTemplate.convertSendAndReceiveAsType(
                props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
                props.getKey(RabbitKeys.FIND_CONTAINERS_KEY),
                this,
                object: ParameterizedTypeReference<ContainersPaginationMessageDto>() {}
            )?.containers?.toContainersPagination()
        }

    override fun placeContainer(placedContainer: PlacedContainer) {
        placedContainer.toPlacementContainerTransactionDto().let { placementContainer ->
            amqpTemplate.convertAndSend(
                props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
                props.getKey(RabbitKeys.PLACEMENT_CONTAINER_KEY),
                PlacementContainerMessage(placementContainer)
            )
        }
    }
}