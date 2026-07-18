package ru.bz.baltic_shipyard_inventory_service.applications.usecases.measuredremainder

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.AbortReason
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.Violation
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.InventoryMeasuredRemainderCommand
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository
import java.time.LocalDate


@Service
class InventoryMeasuredRemainderUseCase(
    private val repository: MeasuredRemainderRepository
) {

    @Transactional
    operator fun invoke(
        command: InventoryMeasuredRemainderCommand,
        userLogin: String
    ): List<Result<MeasuredRemainder>> {
        command.alingWarehouseCodeAndBinCode()
        command.reSetCurrentInventoryDateTime()
        return command.updateMeasuredRemainderCommands.map { updateCommand ->
            repository.findById(updateCommand.id)?.also {
                it.update(updateCommand)
            }?.let { measuredRemainder ->
                repository.update(measuredRemainder, userLogin)
            } ?: throw ResourceNotFoundException()
        }
    }
}