package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.*
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction

interface MeasuredRemainderRepository {
    fun findByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination?
    fun findById(id: String): MeasuredRemainder?
    fun saveForUpdate(measuredRemainder: MeasuredRemainderForUpdate): UpdatableMeasuredRemainder
    fun saveForInventory(measuredRemainders: List<MeasuredRemainder>): InventoriedMeasuredRemainders
    fun findUpdatableMeasuredRemaindersByTransactionId(id: Int): List<UpdatableMeasuredRemainder>
    fun findUpdatableMeasuredRemaindersByTransactions(transactions: List<Transaction>): List<UpdatableMeasuredRemainder>
    fun saveUpdatableMeasuredRemainder(updatableMeasuredRemainder: UpdatableMeasuredRemainder): UpdatableMeasuredRemainder
    fun saveInventoriedMeasuredRemainder(inventoriedMeasuredRemainders: InventoriedMeasuredRemainders): InventoriedMeasuredRemainders
}