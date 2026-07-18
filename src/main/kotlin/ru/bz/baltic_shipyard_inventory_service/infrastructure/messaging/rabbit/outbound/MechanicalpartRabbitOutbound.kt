package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound

import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation

interface MechanicalpartRabbitOutbound {
    fun reportOperation(reportedOperation: ReportedOperation): ReportedOperation?

}