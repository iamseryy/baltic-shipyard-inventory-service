package ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason

import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields

data class AbortReason(
    val violationByFields: ViolationByFields? = null,
    val generalViolation: Violation? = null
)