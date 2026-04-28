package ru.bz.baltic_shipyard_inventory_service.domain.usecases.container

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainerAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.toPlacementContainerAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.Violation
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ContainerRepository
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.inventory.InventoryUseCases
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.warehouse.WarehouseUseCases


@Component
class UseCaseValidatePlacementContainer(
    private val warehouseUseCases: WarehouseUseCases,
    private val containerRepository: ContainerRepository,
    private val inventoryUseCases: InventoryUseCases,
    private val messageSource: MessageSource
) {
    operator fun invoke(placementContainer: PlacementContainer): PlacementContainerAbortReason =
        buildViolationByFields(placementContainer).let { violationByFields ->
            if (violationByFields.violationExists()) AbortReason(violationByFields, null) else null
        }.let { abortReason ->
            placementContainer.toPlacementContainerAbortReason(abortReason)
        }


    private fun buildViolationByFields(placementContainer: PlacementContainer): ViolationByFields =
         ViolationByFields.EntryBuilder()
             .let { builder ->
                containerCodeViolationToBuilder(builder, placementContainer)
            }.let { builder ->
                warehouseViolationToBuilder(builder, placementContainer)
            }.build()


    private fun containerCodeViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        placementContainer: PlacementContainer
    ): ViolationByFields.EntryBuilder {
        containerRepository.findContainerByCode(placementContainer.containerCode).let { container ->
            if(container == null) {
                return builder.containerCodeViolation(
                    Violation(
                        code = messageSource.getMessage("error.404.code", null, LocaleContextHolder.getLocale()),
                        description = messageSource.getMessage("error.404.description", null, LocaleContextHolder.getLocale())
                    )
                )
            }

            if(container.status != ContainerStatus.FOR_PLACEMENT){
                return builder.containerCodeViolation(
                    Violation(
                        code = messageSource.getMessage("error.1212.code", null, LocaleContextHolder.getLocale()),
                        description = messageSource.getMessage("error.1212.description", null, LocaleContextHolder.getLocale())
                    )
                )
            }

            if (container.stock.containerStock.isEmpty()){
                builder.containerCodeViolation(
                    Violation(
                        code = messageSource.getMessage("error.1211.code", null, LocaleContextHolder.getLocale()),
                        description = messageSource.getMessage("error.1211.description", null, LocaleContextHolder.getLocale())
                    )
                )
            }
        }

        return builder
    }

    private fun warehouseViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        placementContainer: PlacementContainer
    ): ViolationByFields.EntryBuilder {
        containerRepository.findContainerByCode(placementContainer.containerCode).let {container ->
            if(container != null &&
                !warehouseUseCases.binOfWarehouseExists(
                    SearchLocationDetailShotFilter(
                        container.location.warehouseCode,
                        placementContainer.binCodeTarget
                    )
                )){
                    builder.binCodeViolation(
                        Violation(
                            code = messageSource.getMessage("error.404.code", null, LocaleContextHolder.getLocale()),
                            description = messageSource.getMessage("error.404.description", null, LocaleContextHolder.getLocale())
                        )
                    )
                    return builder
            }

            if (container != null &&
                    inventoryUseCases.findInventoryBalanceByFilter(
                        SearchInventoryBalanceFilter( warehouseCode = container.location.warehouseCode, binCode = placementContainer.binCodeTarget)
                    ).stockList.isNotEmpty()
                ){
                    builder.binCodeViolation(
                        Violation(
                            code = messageSource.getMessage("error.1210.code", null, LocaleContextHolder.getLocale()),
                            description = messageSource.getMessage("error.1210.description", null, LocaleContextHolder.getLocale())
                        )
                    )
            }
        }

        return builder
    }
}