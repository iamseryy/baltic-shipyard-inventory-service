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
import ru.bz.baltic_shipyard_inventory_service.applications.usecases.measuredremainder.MeasuredRemainderUseCases
import ru.bz.baltic_shipyard_inventory_service.applications.usecases.transaction.TransactionUseCases
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration.security.JwtService
import ru.bz.baltic_shipyard_inventory_service.presentation.controller.ResponseCodes
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.toAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.*
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.filter.MeasuredRemainderFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.filter.toDomainPageRequest
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.filter.toMeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderFilterValidationResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderUpdateValidationResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.toMeasuredRemainderUpdateAbortReasonDto


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}", "api/v2")
@Tag(
    name="springdoc.controllerdefinition.measuredremainder.name",
    description="springdoc.controllerdefinition.measuredremainder.desc"
)
class MeasuredRemainderControllerV1(
    private val transactionUseCases: TransactionUseCases,
    private val useCases: MeasuredRemainderUseCases,
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
    fun getById(
        @PathVariable @Parameter(
            description="springdoc.operationdefinition.measuredremainder.findbyid.param.desc",
            required = true,
            example = "R202011040000000009"
        ) id: String
    ): MeasuredRemainderDto = useCases.getById(id).toMeasuredRemainderDto()


    @PostMapping("/measured-remainders")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.measuredremainder.search.summary",
        description="springdoc.operationdefinition.measuredremainder.search.desc",
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
                        schema = Schema(implementation = MeasuredRemaindersPageResponseDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = MeasuredRemainderFilterValidationResponse::class)
                    )
                )
            )
        ]
    )
    fun search(
        @Valid @RequestBody filterDto: MeasuredRemainderFilterDto
    ): MeasuredRemaindersPageResponseDto =
        useCases.search(
            filter = filterDto.toMeasuredRemainderFilter(),
            pageRequest = filterDto.toDomainPageRequest()
        ).toMeasuredRemaindersPageResponseDto()



    @PatchMapping("/measured-remainders/{id}")
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
                        schema = Schema(implementation = MeasuredRemainderDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = MeasuredRemainderUpdateValidationResponse::class)
                    )
                )
            )
        ]
    )
    fun update(
        @RequestHeader("Authorization") token: String,
        @PathVariable @Parameter(
            description="springdoc.operationdefinition.measuredremainder.findbycode.param.desc",
            required = true,
            example = "R202011040000000009"
        ) id: String,
        @Valid @RequestBody updateDto: MeasuredRemainderUpdateDto
    ) = jwtService.extractUsername(token.substringAfterLast(" ")).let { jwtUser ->
        useCases.update(
            id = id,
            command = updateDto.toUpdateMeasuredRemainderCommand(id),
            userLogin = jwtUser
        ).let { result ->
            when (result) {
                is Result.Success -> result.data.toMeasuredRemainderDto()
                is Result.Failure -> MeasuredRemainderUpdateValidationResponse(
                    id = id,
                    updateDto = updateDto,
                    abortReasonDto = result.toMeasuredRemainderUpdateAbortReasonDto()
                )
            }
        }
    }

    @PostMapping("/measured-remainders/inventory")
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
                        schema = Schema(implementation = MeasuredRemainderInventoryDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = MeasuredRemainderUpdateValidationResponse::class)
                    )
                )
            )
        ]
    )
    fun inventory(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody inventoryDto: MeasuredRemainderInventoryDto
    ) = jwtService.extractUsername(token.substringAfterLast(" ")).let { jwtUser ->
        useCases.inventory(
            command = inventoryDto.toInventoryMeasuredRemainderCommand(),
            userLogin = jwtUser
        ).let { results ->
            results.map { result ->
                when (result) {
                    is Result.Success<MeasuredRemainder> -> result.data.toMeasuredRemainderDto()
                    is Result.Failure -> result.abortReason.toAbortReasonDto()
                }
            }
        }
    }

    @GetMapping("/measured-remainders/bin-codes")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.getbincodesbywarehousecode.summary",
        description="springdoc.operationdefinition.getbincodesbywarehousecode.desc",
        responses = [
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_200_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_200,
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = Array<String>::class)
                    )
                ]
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = [Content(schema = Schema(hidden = true))]
            ),
            ApiResponse(
                responseCode = ResponseCodes.RESPONSE_CODE_404_DESC,
                description = ResponseCodes.RESPONSE_CODE_404,
                content = arrayOf(Content(schema = Schema(hidden = true)))
            )
        ]
    )
    fun getBinCodesByWarehouseCode(
        @RequestParam @Parameter(
            description = "springdoc.operationdefinition.measuredremainder.getBinCodesByWarehouseCode.param.desc",
            required = true,
            example = "R0200"
        ) warehouseCode: String
    ): List<String> = useCases.getBinCodesByWarehouseCode(warehouseCode)

}