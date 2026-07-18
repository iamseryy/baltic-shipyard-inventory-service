package ru.bz.baltic_shipyard_inventory_service.applications.usecases.measuredremainder

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderCommand
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Component
class UpdateMeasuredRemainderUseCase(
    private val repository: MeasuredRemainderRepository
) {
    operator fun invoke(
        id: String,
        command: UpdateMeasuredRemainderCommand,
        userLogin: String
    ): Result<MeasuredRemainder> =
        repository.findById(id)?.also {

            it.update(command)
        }?.let { measuredRemainder ->
            repository.update(measuredRemainder, userLogin)
        } ?: throw ResourceNotFoundException()
}