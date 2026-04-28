package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ItemRepository


@Service
class ItemRepositoryImpl(
    private val rabbitRepository: RabbitRepository
): ItemRepository {
    override fun getItemDetail(itemCode: String): Item? = rabbitRepository.getItemDetail(itemCode)
}