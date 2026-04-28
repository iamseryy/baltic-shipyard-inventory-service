package ru.bz.baltic_shipyard_inventory_service.domain.usecases.mechanicalpart

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MechanicalpartRepoitory


@Component
class ReportOperationUseCase(
    private val mechanicalpartRepoitory: MechanicalpartRepoitory
) {
    operator fun invoke(reportedOperation: ReportedOperation): ReportedOperation =
        mechanicalpartRepoitory.reportedOperation(reportedOperation) ?: throw ResourceNotFoundException()
}