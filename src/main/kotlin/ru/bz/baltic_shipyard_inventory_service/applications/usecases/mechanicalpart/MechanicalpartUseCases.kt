package ru.bz.baltic_shipyard_inventory_service.applications.usecases.mechanicalpart

import org.springframework.stereotype.Component

@Component
data class MechanicalpartUseCases(
    val reportOperation: ReportOperationUseCase,
    val validateReportedOperation: ValidateReportedOperationUseCase
)