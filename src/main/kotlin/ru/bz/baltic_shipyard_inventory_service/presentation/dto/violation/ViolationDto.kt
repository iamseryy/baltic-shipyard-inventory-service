package ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.Violation


@Schema(description="schema.violationdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationDto(
    @Schema(
        description="schema.violationdto.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="9999"
    )
    @JsonProperty(JsonFields.CODE)  val code: String? = null,

    @Schema(
        description="schema.violationdto.description.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="some example error"
    )
    @JsonProperty(JsonFields.DESCRIPTION) val description: String? = null
)

fun Violation.toViolationDto() = ViolationDto(
    code = code,
    description = description
)
