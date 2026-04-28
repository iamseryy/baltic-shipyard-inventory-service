package ru.bz.baltic_shipyard_inventory_service.presentation.controller.measuredremainder

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
import org.springframework.web.bind.annotation.*
import ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration.security.JwtService
import ru.bz.baltic_shipyard_inventory_service.presentation.controller.ResponseCodes
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchMeasuredRemainderFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchTransactionFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.toSearchMeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.toSearchTransactionFilter
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.measuredremainder.TransactionsOfUpdatableMeasuredRemaindersPaginationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.measuredremainder.toTransactionsOfUpdatableMeasuredRemaindersPaginationDto
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder.MeasuredRemainderUseCases
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.transaction.TransactionUseCases
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.InventoriedMeasuredRemaindersDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.InventoriedMeasuredRemaindersValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.MeasuredRemainderDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.MeasuredRemainderForUpdateDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.MeasuredRemainderForUpdateValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.MeasuredRemaindersPaginationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.SearchMeasuredRemainderFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.mergeWith
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.toMeasuredRemainderDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.toMeasuredRemaindersPaginationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.SearchTransactionFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.TransactionDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.toTransactionDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.toTransactionOfUpdatableMeasuredRemaindersDto


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
@Tag(
    name="springdoc.controllerdefinition.measuredremainder.name",
    description="springdoc.controllerdefinition.measuredremainder.desc"
)
class MeasuredRemainderControllerV1(
    private val transactionUseCases: TransactionUseCases,
    private val measuredRemainderUseCases: MeasuredRemainderUseCases,
    private val jwtService: JwtService
) {
    @GetMapping("/measured-remainders/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.measuredremainder.findbyid.summary",
        description="springdoc.operationdefinition.measuredremainder.findbyid.desc",
        responses = [
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_200_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_200,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = MeasuredRemainderDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(Content(schema = Schema(hidden = true)))
            ),
            ApiResponse(
                responseCode = ResponseCodes.RESPONSE_CODE_404_DESC,
                description = ResponseCodes.RESPONSE_CODE_404,
                content = arrayOf(Content(schema = Schema(hidden = true)))
            )
        ]
    )
    fun findMeasuredRemainderById(
        @Parameter(
            description="springdoc.operationdefinition.measuredremainder.findbyid.param.desc",
            required = true,
            example = "R202011040000000009"
        )
        @PathVariable("id") id: String
    ) = measuredRemainderUseCases.findById(id).toMeasuredRemainderDto()


    @PostMapping("/search/measured-remainders")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.measuredremainder.find.summary",
        description="springdoc.operationdefinition.measuredremainder.find.desc",
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
                        schema = Schema(implementation = MeasuredRemaindersPaginationDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = SearchMeasuredRemainderFilterValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun findMeasuredRemaindersByFilter(
        @Valid @RequestBody filter: SearchMeasuredRemainderFilterDto
    ): MeasuredRemaindersPaginationDto = filter.toSearchMeasuredRemainderFilter().let { searchMeasuredRemainderFilter ->
        measuredRemainderUseCases.findByFilter(searchMeasuredRemainderFilter)
    }.toMeasuredRemaindersPaginationDto()


    @PostMapping("/search/measured-remainders/filter/check")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.validatemeasureremainderfindfilter.summary",
        description="springdoc.operationdefinition.validatemeasureremainderfindfilter.desc",
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
                        schema = Schema(implementation = SearchMeasuredRemainderFilterDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = SearchMeasuredRemainderFilterValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun validateMeasuredRemainderSearchFilter(
        @Valid @RequestBody filter: SearchMeasuredRemainderFilterDto
    ) = filter


    @PostMapping("/measured-remainders/update")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.updatemeasuredremainder.summary",
        description="springdoc.operationdefinition.updatemeasuredremainder.desc",
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
                        schema = Schema(implementation = TransactionDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = MeasuredRemainderForUpdateValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun updateMeasuredRemainder(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody measuredRemainderForUpdateDto: MeasuredRemainderForUpdateDto
    ) = measuredRemainderUseCases.findById(measuredRemainderForUpdateDto.id)
        .let {
            measuredRemainderForUpdateDto.mergeWith(it, jwtService.extractUsername(token.substringAfterLast(" ")))
        }.let {
            measuredRemainderUseCases.saveForUpdate(it)
        }.toTransactionDto()


    @PostMapping("/measured-remainders/inventory")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.inventorymeasuredremainder.summary",
        description="springdoc.operationdefinition.inventorymeasuredremainder.desc",
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
                        schema = Schema(implementation = TransactionDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = InventoriedMeasuredRemaindersValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun inventoryMeasuredRemainders(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody inventoriedMeasuredRemaindersDto: InventoriedMeasuredRemaindersDto
    ) = inventoriedMeasuredRemaindersDto.measuredRemainders.map { measuredRemainderUseCases.findById(it.id) }
        .let {
            inventoriedMeasuredRemaindersDto.mergeWith(it)
        }.let {
            measuredRemainderUseCases.saveForInventory(it)
        }.transaction.toTransactionDto()


    @GetMapping("/measured-remainders/transaction/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.transaction.findbyid.summary",
        description="springdoc.operationdefinition.transaction.findbyid.desc",
        responses = [
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_200_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_200,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = TransactionDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(Content(schema = Schema(hidden = true)))
            ),
            ApiResponse(
                responseCode = ResponseCodes.RESPONSE_CODE_404,
                description = ResponseCodes.RESPONSE_CODE_404_DESC,
                content = arrayOf(Content(schema = Schema(hidden = true)))
            )
        ]
    )
    fun findMeasuredRemaindersTransactionByTransactionId(
        @Parameter(
            description="springdoc.operationdefinition.transaction.findbyid.param.desc",
            required = true,
            example = "10"
        )
        @PathVariable("id") id: Int
    ) = transactionUseCases.findById(id)
        .toTransactionDto()
        .toTransactionOfUpdatableMeasuredRemaindersDto(
            measuredRemainderUseCases.findUpdatableMeasuredRemaindersByTransactionId(id)
        )

    @PostMapping("/measured-remainders/search/transactions")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.measuredremainders.transactions.find.summary",
        description="springdoc.operationdefinition.measuredremainder.transactions.find.desc",
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
                        schema = Schema(implementation = TransactionsOfUpdatableMeasuredRemaindersPaginationDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = SearchTransactionFilterValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun findTransactionsOfMeasuredRemaindersByFilterPagination(
        @Valid @RequestBody filter: SearchTransactionFilterDto
    ) = filter.toSearchTransactionFilter().let { searchTransactionFilter ->
        transactionUseCases.findTransactionsOfMeasuredRemaindersByFilterPagination(searchTransactionFilter)
    }.toTransactionsOfUpdatableMeasuredRemaindersPaginationDto()
}