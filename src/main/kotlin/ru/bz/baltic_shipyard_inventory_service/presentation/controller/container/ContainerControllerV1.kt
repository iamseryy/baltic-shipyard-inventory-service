package ru.bz.baltic_shipyard_inventory_service.presentation.controller.container

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
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchContainerFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.toSearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.TransactionDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.toTransactionDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.transfer.TransactionOfTransferredItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.container.ContainerUseCases
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container.TransactionOfPlacedContainerDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container.toTransactionOfPlacedContainerDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.ContainerDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.ContainersPaginationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.PlacementContainerDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.PlacementContainerValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.SearchContainerFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.toContainerDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.toContainersPaginationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.toPlacedContainerDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.toPlacementContainer


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
@Tag(
    name="springdoc.controllerdefinition.container.name",
    description="springdoc.controllerdefinition.container.desc"
)
class ContainerControllerV1 (
    private val containerUseCases: ContainerUseCases,
    private val jwtService: JwtService
){

    @GetMapping("/warehouse/container/{code}")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.container.findbycode.summary",
        description="springdoc.operationdefinition.container.findbycode.desc",
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
                        schema = Schema(implementation = ContainerDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(Content(schema = Schema(hidden = true)))
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
    fun findContainerByCode(
        @Parameter(
            description="springdoc.operationdefinition.container.findbycode.param.desc",
            required = true,
            example = "R020000014"
        )
        @PathVariable("code") code: String
    ) = containerUseCases.findContainerByCode(code).toContainerDto()


    @PostMapping("/warehouse/containers")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.containers.findbyfilter.summary",
        description="springdoc.operationdefinition.containers.findbyfilter.desc",
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
                        schema = Schema(implementation = ContainersPaginationDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = SearchContainerFilterValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun findContainersDetailByFilter(
        @Valid @RequestBody filter: SearchContainerFilterDto
    ): ContainersPaginationDto = filter.toSearchContainerFilter().let {
        containerUseCases.findContainersByFilter(it)
    }.toContainersPaginationDto()


    @PostMapping("/warehouse/container/placement")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.container.placement.summary",
        description="springdoc.operationdefinition.container.placement.desc",
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
                        schema = Schema(implementation = PlacementContainerValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun placementContainer(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody placementData: PlacementContainerDto
    ) = jwtService.extractUsername(token.substringAfterLast(" ")).let {userLogin ->
        placementData.toPlacementContainer(userLogin)
    }.let { placementContainer ->
        with(containerUseCases.validatePlacementContainer(placementContainer)){
            if(abortReason != null) ResponseEntity.status(400).body(this.toPlacedContainerDto())
            else containerUseCases.placeContainer(placementContainer).toTransactionDto()
        }
    }


    @GetMapping("warehouse/container/placement/transaction/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.container.placement.find.by.transactionid.summary",
        description="springdoc.operationdefinition.container.placement.find.by.transactionid.desc",
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
    fun findPlacementContainerTransactionByTransactionId(
        @Parameter(
            description="springdoc.operationdefinition.transfer.item.by.location.find.by.transactionid.param.desc",
            required = true,
            example = "31"
        )
        @PathVariable("id") id: Int
    ): TransactionOfPlacedContainerDto =
        containerUseCases.findPlacedContainerByTransactionId(id).toTransactionOfPlacedContainerDto()

}