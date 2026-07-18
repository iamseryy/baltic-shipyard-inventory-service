package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.common.abortreason

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.AbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.violation.ViolationByFieldsDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.violation.toViolationByField


@JsonInclude(JsonInclude.Include.NON_NULL)
data class AbortReasonDto(
    @param:JsonProperty(JsonFieldsProvider.ERRORS) val violationsByFields: ViolationByFieldsDto? = null,
    @param:JsonProperty(JsonFieldsProvider.GENERAL_VIOLATION) val generalViolation: ViolationDto? = null
)

fun AbortReasonDto.toAbortReason() = AbortReason(
    violationByFields = violationsByFields?.toViolationByField(),
    generalViolation = generalViolation?.toViolation()

)



