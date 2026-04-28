package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationByFieldsDbDto(
    @JsonProperty(EntityFields.REMAINDER) val remainderViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.PROJECT) val projectViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.MATERIAL) val materialViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.WAREHOUSE) val warehouseViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.WAREHOUSE_CODE) val warehouseCodeViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.BIN_CODE) val binCodeViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.BIN_CODE_SOURCE) val binCodeSourceViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.BIN_CODE_TARGET) val binCodeTargetViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.LOCATION) val locationViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.LOCATION_SOURCE) val locationSourceViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.LOCATION_TARGET) val locationTargetViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.SEQUENCE) val sequenceViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.STATUS) val statusViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.COMMENT) val commentViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.LENGTH) val lengthViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.WIDTH) val widthViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.DEPTH) val depthViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.ITEM) val itemViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.ITEM_CODE) val itemCodeViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.LOT) val lotViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.LOT_CODE) val lotCodeViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.QUANTITY) val quantityViolation: ViolationDbDto? = null,
    @JsonProperty(EntityFields.CONTAINER_CODE) val containerCodeViolation: ViolationDbDto? = null
)

fun ViolationByFields.toViolationByFieldsDbDto() = ViolationByFieldsDbDto(
    remainderViolation = remainderViolation?.toViolationDbDto(),
    projectViolation = projectViolation?.toViolationDbDto(),
    materialViolation = materialViolation?.toViolationDbDto(),
    warehouseViolation = warehouseViolation?.toViolationDbDto(),
    warehouseCodeViolation = warehouseViolation?.toViolationDbDto(),
    locationViolation = locationViolation?.toViolationDbDto(),
    locationSourceViolation = locationSourceViolation?.toViolationDbDto(),
    locationTargetViolation = locationTargetViolation?.toViolationDbDto(),
    sequenceViolation = sequenceViolation?.toViolationDbDto(),
    statusViolation = statusViolation?.toViolationDbDto(),
    commentViolation = commentViolation?.toViolationDbDto(),
    lengthViolation = lengthViolation?.toViolationDbDto(),
    widthViolation = widthViolation?.toViolationDbDto(),
    depthViolation = depthViolation?.toViolationDbDto(),
    itemViolation = itemViolation?.toViolationDbDto(),
    itemCodeViolation = itemCodeViolation?.toViolationDbDto(),
    lotViolation = lotViolation?.toViolationDbDto(),
    lotCodeViolation = lotCodeViolation?.toViolationDbDto(),
    quantityViolation = quantityViolation?.toViolationDbDto(),
    binCodeSourceViolation = binCodeSourceViolation?.toViolationDbDto(),
    binCodeTargetViolation = binCodeTargetViolation?.toViolationDbDto(),
    containerCodeViolation = containerCodeViolation?.toViolationDbDto(),
    binCodeViolation = binCodeViolation?.toViolationDbDto()
)

fun ViolationByFieldsDbDto.toViolationByFields() = ViolationByFields(
    remainderViolation = remainderViolation?.toViolation(),
    projectViolation = projectViolation?.toViolation(),
    materialViolation = materialViolation?.toViolation(),
    warehouseViolation = warehouseViolation?.toViolation(),
    warehouseCodeViolation = warehouseCodeViolation?.toViolation(),
    locationViolation = locationViolation?.toViolation(),
    locationSourceViolation = locationSourceViolation?.toViolation(),
    locationTargetViolation = locationTargetViolation?.toViolation(),
    sequenceViolation = sequenceViolation?.toViolation(),
    statusViolation = statusViolation?.toViolation(),
    commentViolation = commentViolation?.toViolation(),
    lengthViolation = lengthViolation?.toViolation(),
    widthViolation = widthViolation?.toViolation(),
    depthViolation = depthViolation?.toViolation(),
    itemViolation = itemViolation?.toViolation(),
    itemCodeViolation = itemCodeViolation?.toViolation(),
    lotViolation = lotViolation?.toViolation(),
    lotCodeViolation = lotCodeViolation?.toViolation(),
    quantityViolation = quantityViolation?.toViolation(),
    containerCodeViolation = containerCodeViolation?.toViolation(),
    binCodeTargetViolation = binCodeTargetViolation?.toViolation(),
    binCodeSourceViolation = binCodeSourceViolation?.toViolation(),
    binCodeViolation = binCodeViolation?.toViolation()
)
