package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.LotRepository


@Service
class LotRepositoryImpl(
    private val rabbitRepository: RabbitRepository
): LotRepository {
    override fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail? = rabbitRepository.getLotDetail(filter)
}