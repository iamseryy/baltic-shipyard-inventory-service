package ru.bz.baltic_shipyard_inventory_service.domain.usecases.lot

import org.springframework.stereotype.Service

@Service
data class LotUseCases(
    val getLotDetailByShotFilter: UseCaseGetLotDetailByShotFilter,
    val lotExists: UseCaseLotExists
)
