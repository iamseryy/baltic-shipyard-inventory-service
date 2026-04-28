package ru.bz.baltic_shipyard_inventory_service.presentation.controller.mechanicalpart

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
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.mechanicalpart.MechanicalpartUseCases
import ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration.security.JwtService
import ru.bz.baltic_shipyard_inventory_service.presentation.controller.ResponseCodes
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.mechanicalpart.ReportedOperationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.mechanicalpart.ReportedOperationValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.mechanicalpart.toReportedOperation
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.mechanicalpart.toReportedOperationDto


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
@Tag(
    name="springdoc.controllerdefinition.mechanicalpart.name",
    description="springdoc.controllerdefinition.mechanicalpart.desc"
)
class MechanicalpartControllerV1(
    private val mechanicalpartUseCases: MechanicalpartUseCases,
    private val jwtService: JwtService
) {
    @PostMapping("/mechanicalpart/operation/reporting")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.mechanicalpart.operation.reporting.summary",
        description="springdoc.operationdefinition.mechanicalpart.operation.reporting.desc",
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
                        schema = Schema(implementation = ReportedOperationDto::class)
                    )
                )
            ),
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_400_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_400,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ReportedOperationValidationErrorResponse::class)
                    )
                )
            )
        ]
    )
    fun onReportOperation(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody reportedOperation: ReportedOperationDto
    ) = jwtService.extractUsername(token.substringAfterLast(" ")).let {userLogin ->
        reportedOperation.toReportedOperation(userLogin)
    }.let { operation ->
        with(mechanicalpartUseCases.validateReportedOperation(operation)){
            if(abortReason != null) ResponseEntity.status(400).body(this.toReportedOperationDto())
            else mechanicalpartUseCases.reportOperation(operation).toReportedOperationDto()
        }
    }
}