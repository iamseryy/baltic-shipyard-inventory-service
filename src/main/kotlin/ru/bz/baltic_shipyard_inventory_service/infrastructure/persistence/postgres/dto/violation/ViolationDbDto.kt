package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.Violation


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationDbDto(
    @JsonProperty("code") val code: String,
    @JsonProperty("description") val externalDescription: String? = null
)

fun Violation.toViolationDbDto() = ViolationDbDto(
    code = code,
    externalDescription = externalDescription
)

fun ViolationDbDto.toViolation() = Violation(
    code = code,
    externalDescription = externalDescription
)

