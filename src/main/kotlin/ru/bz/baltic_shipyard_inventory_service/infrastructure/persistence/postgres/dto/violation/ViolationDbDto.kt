package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.Violation


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationDbDto(
    @JsonProperty("code") val code: String? = null,
    @JsonProperty("description") val description: String? = null
)

fun Violation.toViolationDbDto() = ViolationDbDto(
    code = code,
    description = description
)

fun ViolationDbDto.toViolation() = Violation(
    code = code,
    description = description
)

