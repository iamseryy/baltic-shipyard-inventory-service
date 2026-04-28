package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail

interface LotRepository {
    fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail?
}