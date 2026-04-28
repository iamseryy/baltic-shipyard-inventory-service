package ru.bz.baltic_shipyard_inventory_service.presentation.controller.transfer


import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration.security.JwtService
import ru.bz.baltic_shipyard_inventory_service.presentation.controller.ResponseCodes
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.TransactionDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.toTransactionDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.transfer.TransactionOfTransferredItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.transfer.toTransactionOfTransferredItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.TransferItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.TransferItemByLocationsValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.toTransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.toTransferredItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.transfer.TransferUseCases
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.validation.ValidationUseCases


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
@Tag(
    name="springdoc.controllerdefinition.transfer.name",
    description="springdoc.controllerdefinition.transfer.desc"
)
class TransferControllerV1 (
    private val transferUseCases: TransferUseCases,
    private val jwtService: JwtService,
    private val validationUseCases: ValidationUseCases
){
    @PostMapping("/warehouse/transfer/from_location_to_location")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.transfer.item.by.location.summary",
        description="springdoc.operationdefinition.transfer.item.by.location.desc",
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
                        schema = Schema(implementation = TransferItemByLocationsValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun transferItemByBin(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody transferItemByLocationsDto: TransferItemByLocationsDto
    ) = jwtService.extractUsername(token.substringAfterLast(" "))
        .let {userName ->
            transferItemByLocationsDto.toTransferItemByLocations(userName)
        }.let {transferItemByLocations ->
            with(validationUseCases.validateTransferItemByLocation(transferItemByLocations)){
                if(abortReason != null) ResponseEntity.status(400).body(this.toTransferredItemByLocationsDto())
                else transferUseCases.transferItemByLocations(transferItemByLocations).toTransactionDto()
            }
        }

    @GetMapping("/warehouse/transfer/from_location_to_location/transaction/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.transfer.item.by.location.find.by.transactionid.summary",
        description="springdoc.operationdefinition.transfer.item.by.location.find.by.transactionid.desc",
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
                        schema = Schema(implementation = TransactionOfTransferredItemByLocationsDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_404_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_404,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ViolationDto::class)
                    )
                )
            )
        ]
    )
    fun findTransferItemByLocationsTransactionByTransactionId(
        @Parameter(
            description="springdoc.operationdefinition.transfer.item.by.location.find.by.transactionid.param.desc",
            required = true,
            example = "31"
        )
        @PathVariable("id") id: Int
    ) = transferUseCases
        .findTransferredItemByLocationsByTransactionId(id)
        .toTransactionOfTransferredItemByLocationsDto()
}