package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.common.abortreason

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.Violation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationDto(
    @param:JsonProperty(JsonFieldsProvider.CODE) val code: String,
    @param:JsonProperty(JsonFieldsProvider.DESCRIPTION) val description: String?
)

fun ViolationDto.toViolation() = Violation(
    code = code,
    externalDescription = description
)
