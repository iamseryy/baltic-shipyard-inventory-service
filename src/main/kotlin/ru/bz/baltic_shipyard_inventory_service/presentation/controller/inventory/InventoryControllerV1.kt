package ru.bz.baltic_shipyard_inventory_service.presentation.controller.inventory

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.bz.baltic_shipyard_inventory_service.presentation.controller.ResponseCodes
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchChildrenInventoryBalanceFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchInventoryBalanceFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.toSearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory.SearchChildrenInventoryBalanceFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory.SearchInventoryBalanceFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory.StockListPaginationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory.toStockListPaginationDto
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.inventory.InventoryUseCases


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
@Tag(
    name="springdoc.controllerdefinition.inventory.name",
    description="springdoc.controllerdefinition.inventory.desc"
)
class InventoryControllerV1(
    private val inventoryUseCases: InventoryUseCases
) {

    @PostMapping("/warehouse/inventory-balance")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.inventory.balance.get.summary",
        description="springdoc.operationdefinition.inventory.balance.get.desc",
        parameters = [
            Parameter(
                `in` = ParameterIn.HEADER,
                name = "Accept-Language",
                schema = Schema(type = "string", allowableValues = ["en", "ru"], defaultValue = "en"),
                example = "ru"
            )],
        responses = [
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_200_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_200,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = StockListPaginationDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = SearchInventoryBalanceFilterValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun getInventoryBalanceByFilter(
        @Valid @RequestBody filter: SearchInventoryBalanceFilterDto
    ): StockListPaginationDto = filter.toSearchInventoryBalanceFilter().let {
        inventoryUseCases.findInventoryBalanceByFilter(it)
    }.toStockListPaginationDto()

    @PostMapping("/warehouse/inventory-balance-child-lot")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.children.inventory.balance.get.summary",
        description="springdoc.operationdefinition.children.inventory.balance.get.desc",
        parameters = [
            Parameter(
                `in` = ParameterIn.HEADER,
                name = "Accept-Language",
                schema = Schema(type = "string", allowableValues = ["en", "ru"], defaultValue = "en"),
                example = "ru"
            )],
        responses = [
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_200_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_200,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = StockListPaginationDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = SearchChildrenInventoryBalanceFilterValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun findInventoryBalanceWithChildrenByLot(
        @Valid @RequestBody filter: SearchChildrenInventoryBalanceFilterDto
    ): StockListPaginationDto = filter.toSearchInventoryBalanceFilter().let {
        inventoryUseCases.findInventoryBalanceByFilter(it)
    }.toStockListPaginationDto()
}
