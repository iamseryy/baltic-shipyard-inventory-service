package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.lot

import org.springframework.stereotype.Service

@Service
data class LotUseCases(
    val getLotDetailByShotFilter: GetLotDetailByShotFilterUseCase,
    val lotExists: LotExistsUseCase
)