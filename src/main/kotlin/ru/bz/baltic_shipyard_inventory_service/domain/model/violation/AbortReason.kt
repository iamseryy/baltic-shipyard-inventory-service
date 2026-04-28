package ru.bz.baltic_shipyard_inventory_service.domain.model.violation

data class AbortReason(
    val violationByFields: ViolationByFields?,
    val generalViolation: Violation?
)
