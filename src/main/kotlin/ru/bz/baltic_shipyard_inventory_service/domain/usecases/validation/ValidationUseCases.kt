package ru.bz.baltic_shipyard_inventory_service.domain.usecases.validation

import org.springframework.stereotype.Service


@Service
data class ValidationUseCases(
    val validateTransferItemByLocation: UseCaseValidateTransferItemByLocation
)
