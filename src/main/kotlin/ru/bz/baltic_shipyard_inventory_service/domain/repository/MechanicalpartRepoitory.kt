package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation

interface MechanicalpartRepoitory {
    fun reportedOperation(reportedOperation: ReportedOperation): ReportedOperation?
}