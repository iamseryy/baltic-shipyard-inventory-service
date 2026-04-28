package ru.bz.baltic_shipyard_inventory_service.domain.usecases.item

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ItemRepository


@Service
class UseCaseGetItemDetailByItemCode(
    private val itemRepository: ItemRepository
) {
    operator fun invoke(itemCode: String): Item = itemRepository.getItemDetail(itemCode) ?: throw ResourceNotFoundException()
}