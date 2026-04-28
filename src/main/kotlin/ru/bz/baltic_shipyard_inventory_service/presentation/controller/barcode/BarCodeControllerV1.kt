package ru.bz.baltic_shipyard_inventory_service.presentation.controller.barcode

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.bz.baltic_shipyard_inventory_service.presentation.controller.ResponseCodes
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.barcode.BarCodeUseCases


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
@Tag(
    name="springdoc.controllerdefinition.barcode.name",
    description="springdoc.controllerdefinition.barcode.desc"
)
class BarCodeControllerV1(
    private val barCodeUseCases: BarCodeUseCases
) {
    @GetMapping("/barcode/{code}")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.barcode.findbycode.summary",
        description="springdoc.operationdefinition.barcode.findbycode.desc",
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
                        schema = Schema(
                            type = "object",
                            examples = arrayOf( """
                                    {
                                        "code": "R020000014"
                                    }
                                    """)
                        ),
                        examples = arrayOf(
                            ExampleObject(
                                name = "Container code",
                                value = """
                                    {
                                        "code": "R020000014"
                                    }
                                """
                            ),
                            ExampleObject(
                                name = "Warehouse",
                                value = """
                                    {
                                        "code": "R0200",
                                        "description": "some description"
                                    }
                                """

                            )
                        )
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
    fun findDataByBarCode(
        @Parameter(
            description="springdoc.operationdefinition.barcode.findbycode.param.desc",
            required = true,
            example = "R020000014"
        )
        @PathVariable("code") code: String
    ): Map<String, Any> =  barCodeUseCases.findDataByBarCode(code).barCodeData
}