package ru.bz.baltic_shipyard_inventory_service.domain.usecases.validation

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocationAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.toTransferItemByLocationAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.Violation
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.inventory.InventoryUseCases
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.item.ItemUseCases
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.lot.LotUseCases
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.warehouse.WarehouseUseCases


@Service
class UseCaseValidateTransferItemByLocation(
    private val warehouseUseCases: WarehouseUseCases,
    private val itemUseCases: ItemUseCases,
    private val lotUseCases: LotUseCases,
    private val inventoryUseCases: InventoryUseCases,
    private val messageSource: MessageSource
) {
    operator fun invoke(transfer: TransferItemByLocations): TransferItemByLocationAbortReason =
        buildViolationByFields(transfer).let { violationByFields ->
            if (violationByFields.violationExists()) AbortReason(violationByFields, null) else null
        }.let { abortReason ->
            transfer.toTransferItemByLocationAbortReason(abortReason)
        }


    private fun buildViolationByFields(transfer: TransferItemByLocations) =
        ViolationByFields.EntryBuilder().let { builder ->
            warehouseViolationToBuilder(builder, transfer)
        }.let { builder ->
            lotViolationToBuilder(builder, transfer)
        }.let { builder ->
            quantityViolationToBuilder(builder, transfer)
        }.build()



    private fun warehouseViolationToBuilder (
        builder: ViolationByFields.EntryBuilder,
        transfer: TransferItemByLocations
    ): ViolationByFields.EntryBuilder {

        if(!warehouseUseCases.warehouseExists(transfer.warehouseCode)) {
            return  builder.warehouseCodeViolation(
                Violation(
                    code = messageSource.getMessage("error.404.code", null, LocaleContextHolder.getLocale()),
                    description = messageSource.getMessage("error.404.description", null, LocaleContextHolder.getLocale())
                )
            )
        }

        if(!warehouseUseCases.binOfWarehouseExists(SearchLocationDetailShotFilter(transfer.warehouseCode, transfer.binCodeSource))){
            builder.binCodeSourceViolation(
                Violation(
                    code = messageSource.getMessage("error.404.code", null, LocaleContextHolder.getLocale()),
                    description = messageSource.getMessage("error.404.description", null, LocaleContextHolder.getLocale())
                )
            )
        }

        if(!warehouseUseCases.binOfWarehouseExists(SearchLocationDetailShotFilter(transfer.warehouseCode, transfer.binCodeTarget))){
                builder.binCodeTargetViolation(
                    Violation(
                        code = messageSource.getMessage("error.404.code", null, LocaleContextHolder.getLocale()),
                        description = messageSource.getMessage("error.404.description", null, LocaleContextHolder.getLocale())
                    )
                )
        }

        return builder
    }

    private fun lotViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        transfer: TransferItemByLocations
    ): ViolationByFields.EntryBuilder {

        if (!itemUseCases.itemExists(transfer.itemCode)) {
            return  builder.itemCodeViolation(
                Violation(
                    code = messageSource.getMessage("error.404.code", null, LocaleContextHolder.getLocale()),
                    description = messageSource.getMessage("error.404.description", null, LocaleContextHolder.getLocale())
                )
            )
        }

        if (!lotUseCases.lotExists(SearchLotDetailShotFilter(transfer.itemCode, transfer.lotCode))) {
            builder.lotCodeViolation(
                Violation(
                    code = messageSource.getMessage("error.404.code", null, LocaleContextHolder.getLocale()),
                    description = messageSource.getMessage("error.404.description", null, LocaleContextHolder.getLocale())
                )
            )
        }

        return builder
    }


    private fun quantityViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        transfer: TransferItemByLocations
    ): ViolationByFields.EntryBuilder {

        if(builder.violationExists()) return builder

        inventoryUseCases.findInventoryBalanceByFilter(
            SearchInventoryBalanceFilter(
                warehouseCode = transfer.warehouseCode,
                binCode = transfer.binCodeSource,
                itemCode = transfer.itemCode,
                lotCode = transfer.lotCode
            )
        ).stockList.let {stockList ->
            if (stockList.isEmpty() ||
                stockList[0].quantityAvailable - stockList[0].quantityBlocked - stockList[0].quantityAllocated < transfer.quantity ) {
                builder.quantityViolation(
                        Violation(
                            code = messageSource.getMessage("error.1209.code", null, LocaleContextHolder.getLocale()),
                            description = messageSource.getMessage("error.1209.description", null, LocaleContextHolder.getLocale())
                        )
                    )
                }
            }

        return builder
    }
}
