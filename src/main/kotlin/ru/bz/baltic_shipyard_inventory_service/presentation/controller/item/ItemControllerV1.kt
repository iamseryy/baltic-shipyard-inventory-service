package ru.bz.baltic_shipyard_inventory_service.presentation.controller.item

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.bz.baltic_shipyard_inventory_service.presentation.controller.ResponseCodes
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.item.ItemDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.item.toItemDto
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.item.ItemUseCases


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
@Tag(
    name="springdoc.controllerdefinition.item.name",
    description="springdoc.controllerdefinition.item.desc"
)
class ItemControllerV1(
    private val itemUseCases: ItemUseCases
) {

    @GetMapping("/item/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(
        summary="springdoc.operationdefinition.item.findbyid.summary",
        description="springdoc.operationdefinition.item.findbyid.desc",
        responses = [
            ApiResponse(
                description = ResponseCodes.RESPONSE_CODE_200_DESC,
                responseCode = ResponseCodes.RESPONSE_CODE_200,
                content = arrayOf(
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ItemDto::class)
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
    fun findItemById(
        @Parameter(
            description="springdoc.operationdefinition.item.findbyid.param.desc",
            required = true,
            example = "01362783045"
        )
        @PathVariable("id") id: String
    ) = itemUseCases.getItemDetailByItemCode(id).toItemDto()
}