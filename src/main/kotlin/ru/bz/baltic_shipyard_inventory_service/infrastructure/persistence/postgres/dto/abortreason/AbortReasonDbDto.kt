package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.abortreason

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation.ViolationByFieldsDbDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation.ViolationDbDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation.toViolationByFieldsDbDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation.toViolationDbDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields


@JsonInclude(JsonInclude.Include.NON_NULL)
data class AbortReasonDbDto(
    @JsonProperty(EntityFields.ERRORS) val violationByFields: ViolationByFieldsDbDto? = null,
    @JsonProperty(EntityFields.GENERAL) val generalViolation: ViolationDbDto? = null
)

fun AbortReason.toAbortReasonDbDto() = AbortReasonDbDto(
    violationByFields = violationByFields?.toViolationByFieldsDbDto(),
    generalViolation = generalViolation?.toViolationDbDto()
)


