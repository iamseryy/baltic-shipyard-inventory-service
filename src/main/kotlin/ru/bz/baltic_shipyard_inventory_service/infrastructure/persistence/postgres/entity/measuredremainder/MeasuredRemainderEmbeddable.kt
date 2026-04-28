package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.measuredremainder

import jakarta.persistence.Access
import jakarta.persistence.AccessType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.*
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields

@Embeddable
@Access(AccessType.FIELD)
data class MeasuredRemainderEmbeddable(
    @field:Column(name = EntityFields.MEASURED_REMAINDER_ID, nullable = false)
    override val measuredRemainderId: String,

    @field:Column(name = EntityFields.REMAINDER, nullable = false)
    override val remainder: String,

    @field:Column(name = EntityFields.PROJECT, nullable = false)
    override val project: String,

    @field:Column(name = EntityFields.MATERIAL, nullable = false)
    override val material: String,

    @field:Column(name = EntityFields.WAREHOUSE, nullable = false)
    override val warehouse: String,

    @field:Column(name = EntityFields.LOCATION)
    override val location: String,

    @field:Column(name = EntityFields.SEQUENCE)
    override val sequence: Int,

    @field:Column(name = EntityFields.STATUS, nullable = false)
    override val status: MeasuredRemainderStatus,

    @field:Column(name = EntityFields.COMMENT)
    override val comment: String,

    @field:Column(name = EntityFields.LENGTH)
    override val length: Double,

    @field:Column(name = EntityFields.WIDTH)
    override val width: Double,

    @field:Column(name = EntityFields.DEPTH)
    override val depth: Double
): MeasuredRemainderBase

fun UpdatableMeasuredRemainder.toMeasuredRemainderEmbeddable() = MeasuredRemainderEmbeddable(
    measuredRemainderId = measuredRemainderId,
    remainder = remainder,
    project = project,
    material = material,
    warehouse = warehouse,
    location = location,
    sequence = sequence,
    status = status,
    comment = comment,
    length = length,
    width = width,
    depth = depth
)

fun MeasuredRemainderForUpdate.toMeasuredRemainderEmbeddable() = MeasuredRemainderEmbeddable(
    measuredRemainderId = measuredRemainderId,
    remainder = remainder,
    project = project,
    material = material,
    warehouse = warehouse,
    location = location,
    sequence = sequence,
    status = status,
    comment = comment,
    length = length,
    width = width,
    depth = depth
)

fun MeasuredRemainder.toMeasuredRemainderEmbeddable() = MeasuredRemainderEmbeddable(
    measuredRemainderId = measuredRemainderId,
    remainder = remainder,
    project = project,
    material = material,
    warehouse = warehouse,
    location = location,
    sequence = sequence,
    status = status,
    comment = comment,
    length = length,
    width = width,
    depth = depth
)