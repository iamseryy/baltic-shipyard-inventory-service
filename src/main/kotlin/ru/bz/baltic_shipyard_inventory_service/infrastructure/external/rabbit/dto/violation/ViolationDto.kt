package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.violation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.Violation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationDto(
    @param:JsonProperty(JsonFieldsProvider.CODE) val code: String?,
    @param:JsonProperty(JsonFieldsProvider.DESCRIPTION) val description: String?
)

fun ViolationDto.toViolation() = Violation(
    code = code,
    description = description
)
