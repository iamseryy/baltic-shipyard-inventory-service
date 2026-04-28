package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.*
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.measuredremainder.*
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransaction
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.TransactionJpaRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.UpdatableMeasuredRemainderJpaRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Service
@Transactional
class MeasuredRemainderRepositoryImpl(
    private val rabbitRepository: RabbitRepository,
    private val transactionJpaRepository: TransactionJpaRepository,
    private val updatableMeasuredRemainderJpaRepository: UpdatableMeasuredRemainderJpaRepository
): MeasuredRemainderRepository {

    override fun findByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination? =
        rabbitRepository.findMeasuredRemainderByFilter(filter)

    override fun findById(id: String): MeasuredRemainder? =
        SearchMeasuredRemainderFilter.EntryBuilder()
            .id(id)
            .build()
            .let {
                rabbitRepository.findMeasuredRemainderByFilter(it)?.measuredRemainders?.firstOrNull()
            }

    override fun saveForUpdate(measuredRemainder: MeasuredRemainderForUpdate) =
        updatableMeasuredRemainderJpaRepository.save(
            UpdatableMeasuredRemainderEntity(
                measuredRemainder = measuredRemainder.toMeasuredRemainderEmbeddable(),
                transaction = TransactionEntity(userLogin = measuredRemainder.userLogin)
            )
        ).toUpdatableMeasuredRemainder().also {
            rabbitRepository.updateMeasuredRemainder(it)
        }

    override fun saveForInventory(measuredRemainders: List<MeasuredRemainder>): InventoriedMeasuredRemainders {
        return transactionJpaRepository.save(TransactionEntity(userLogin = ""))
            .let { transactionEntity ->
                updatableMeasuredRemainderJpaRepository.saveAll(
                    measuredRemainders.map { measuredRemainder ->
                        UpdatableMeasuredRemainderEntity(
                            measuredRemainder = measuredRemainder.toMeasuredRemainderEmbeddable(),
                            transaction = transactionEntity
                        )
                    }
                ).let {
                    InventoriedMeasuredRemainders(
                        transaction = transactionEntity.toTransaction(),
                        measuredRemainders = it.map { updatableMeasuredRemainderEntity ->
                            updatableMeasuredRemainderEntity.toInventoriedMeasuredRemainder()
                        }
                    )
                }
            }.also {
                rabbitRepository.inventoryMeasuredRemainder(it)
            }
    }

    override fun saveUpdatableMeasuredRemainder(updatableMeasuredRemainder: UpdatableMeasuredRemainder) =
        updatableMeasuredRemainder.toUpdatableMeasuredRemainderEntity().let {
            updatableMeasuredRemainderJpaRepository.save(it)
        }.toUpdatableMeasuredRemainder()


    override fun saveInventoriedMeasuredRemainder(inventoriedMeasuredRemainders: InventoriedMeasuredRemainders) =
        transactionJpaRepository.save(inventoriedMeasuredRemainders.transaction.toTransactionEntity())
            .let { transactionEntity ->
                updatableMeasuredRemainderJpaRepository.saveAll(
                    inventoriedMeasuredRemainders.measuredRemainders.map { inventoriedMeasuredRemainder ->
                            inventoriedMeasuredRemainder.toUpdatableMeasuredRemainderEntity(transactionEntity)}
                )
            }.let {
                InventoriedMeasuredRemainders(
                    transaction = it[0].transaction.toTransaction(),
                    measuredRemainders = it.map{ updatableMeasuredRemainderEntity ->
                        updatableMeasuredRemainderEntity.toInventoriedMeasuredRemainder()}
                )
            }

    override fun findUpdatableMeasuredRemaindersByTransactionId(id: Int) =
        updatableMeasuredRemainderJpaRepository.findByTransactionId(id).map {
            it.toUpdatableMeasuredRemainder()
        }

    override fun findUpdatableMeasuredRemaindersByTransactions(transactions: List<Transaction>) =
        updatableMeasuredRemainderJpaRepository.findAllByTransactionIdIn(transactions.map { it.id })
            .map {it.toUpdatableMeasuredRemainder()}
}


