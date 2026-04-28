package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderDto (
    @JsonProperty(JsonFieldsProvider.ID) val id: String,
    @JsonProperty(JsonFieldsProvider.REMAINDER) val remainder: String,
    @JsonProperty(JsonFieldsProvider.PROJECT) val project: String,
    @JsonProperty(JsonFieldsProvider.MATERIAL) val material: String,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE) val warehouse: String,
    @JsonProperty(JsonFieldsProvider.LOCATION) val location: String,
    @JsonProperty(JsonFieldsProvider.SEQUENCE) val sequence: Int,
    @JsonProperty(JsonFieldsProvider.STATUS) val status: Int,
    @JsonProperty(JsonFieldsProvider.COMMENT) val comment: String,
    @JsonProperty(JsonFieldsProvider.LENGTH) val length: Double,
    @JsonProperty(JsonFieldsProvider.WIDTH) val width: Double,
    @JsonProperty(JsonFieldsProvider.DEPTH) val depth: Double
)

fun MeasuredRemainderDto.toMeasuredRemainder() =
    MeasuredRemainder (
        measuredRemainderId = id,
        remainder = remainder,
        project = project,
        material = material,
        warehouse = warehouse,
        location = location,
        sequence = sequence,
        status = MeasuredRemainderStatus.getByNumber(status),
        comment = comment,
        length = length,
        width = width,
        depth = depth
    )

fun UpdatableMeasuredRemainder.toMeasuredRemainderDto() = MeasuredRemainderDto(
    id = measuredRemainderId,
    remainder = remainder,
    project = project,
    material = material,
    warehouse = warehouse,
    location = location,
    sequence = sequence,
    status = status.number,
    comment = comment,
    length = length,
    width = width,
    depth = depth
)

fun InventoriedMeasuredRemainder.toMeasuredRemainderDto() = MeasuredRemainderDto(
    id = measuredRemainderId,
    remainder = remainder,
    project = project,
    material = material,
    warehouse = warehouse,
    location = location,
    sequence = sequence,
    status = status.number,
    comment = comment,
    length = length,
    width = width,
    depth = depth
)