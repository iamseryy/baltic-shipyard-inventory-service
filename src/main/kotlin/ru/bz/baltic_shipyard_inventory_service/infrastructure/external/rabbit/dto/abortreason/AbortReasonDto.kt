package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.violation.ViolationByFieldsDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.violation.toViolation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.violation.toViolationByField


@JsonInclude(JsonInclude.Include.NON_NULL)
data class AbortReasonDto(
    @param:JsonProperty(JsonFieldsProvider.ERRORS) val violationsByFields: ViolationByFieldsDto? = null,
    @param:JsonProperty(JsonFieldsProvider.GENERAL) val generalViolation: ViolationDto? = null
)

fun AbortReasonDto.toAbortReason() = AbortReason(
    violationByFields = violationsByFields?.toViolationByField(),
    generalViolation = generalViolation?.toViolation()

)



