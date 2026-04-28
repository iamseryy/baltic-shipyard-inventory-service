package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MechanicalpartRepoitory
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository


@Component
class MechanicalpartRepositoryImpl(
    private val rabbitRepository: RabbitRepository
): MechanicalpartRepoitory {
    override fun reportedOperation(reportedOperation: ReportedOperation): ReportedOperation? = rabbitRepository.reportOperation(reportedOperation)
}