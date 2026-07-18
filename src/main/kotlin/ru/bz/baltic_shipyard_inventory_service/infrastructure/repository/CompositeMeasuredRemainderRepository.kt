package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPage
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPageRequest
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.MeasuredRemainderRabbitOutbound

@Service
@Transactional
class CompositeMeasuredRemainderRepository(
    private val rabbitOutbound: MeasuredRemainderRabbitOutbound
): MeasuredRemainderRepository {

    override fun findPage(filter: MeasuredRemainderFilter, pageRequest: DomainPageRequest): DomainPage<MeasuredRemainder>? =
        rabbitOutbound.findPage(filter, pageRequest)

    override fun findById(id: String): MeasuredRemainder? =
        rabbitOutbound.findPage(
            filter = MeasuredRemainderFilter( id = id),
            pageRequest = DomainPageRequest( number = 0, size = 1)
        )?.content?.first()

    override fun update(measuredRemainder: MeasuredRemainder, userLogin: String): Result<MeasuredRemainder> =
        rabbitOutbound.update(measuredRemainder, userLogin)
}