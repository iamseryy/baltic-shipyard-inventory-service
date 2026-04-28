package ru.bz.baltic_shipyard_inventory_service.domain.usecases.item

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ItemRepository


@Service
class UseCaseItemExists(
    private val itemRepository: ItemRepository
) {
    operator fun invoke(itemCode:String) = itemRepository.getItemDetail(itemCode) != null
}