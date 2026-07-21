package ru.bz.baltic_shipyard_inventory_service.applications.usecases.measuredremainder

import org.springframework.stereotype.Service


@Service
data class MeasuredRemainderUseCases(
    val search: SearchMeasuredRemaindersUseCase,
    val getById: GetMeasuredRemainderByIdUseCase,
    val update: UpdateMeasuredRemainderUseCase,
    val inventory: InventoryMeasuredRemainderUseCase,
    val getBinCodesByWarehouseCode: GetBinCodesByWarehouseCodeUseCase
)
