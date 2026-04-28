package ru.bz.baltic_shipyard_inventory_service.domain.usecases.lot

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail
import ru.bz.baltic_shipyard_inventory_service.domain.repository.LotRepository


@Service
class UseCaseGetLotDetailByShotFilter(
    private val lotRepository: LotRepository
) {
    operator fun invoke(filter: SearchLotDetailShotFilter): LotDetail? = lotRepository.getLotDetail(filter)
}