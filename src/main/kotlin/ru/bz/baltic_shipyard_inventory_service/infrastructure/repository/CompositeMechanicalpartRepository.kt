package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MechanicalpartRepoitory
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.MechanicalpartRabbitOutbound

@Component
class CompositeMechanicalpartRepository(
    private val mechanicalpartRabbitOutbound: MechanicalpartRabbitOutbound
): MechanicalpartRepoitory {
    override fun reportedOperation(reportedOperation: ReportedOperation): ReportedOperation? =
        mechanicalpartRabbitOutbound.reportOperation(reportedOperation)
}