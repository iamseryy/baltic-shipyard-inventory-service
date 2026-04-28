package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.violation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ViolationByFieldsDto(
    @param:JsonProperty(JsonFieldsProvider.REMAINDER) val remainderViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.PROJECT) val projectViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.MATERIAL) val materialViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.WAREHOUSE) val warehouseViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCodeViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.LOCATION) val locationViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.LOCATION_SOURCE) val locationSourceViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.LOCATION_TARGET) val locationTargetViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.SEQUENCE) val sequenceViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.STATUS) val statusViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.COMMENT) val commentViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.LENGTH) val lengthViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.WIDTH) val widthViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.DEPTH) val depthViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.ITEM) val itemViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.ITEM_CODE) val itemCodeViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.LOT) val lotViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCodeViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.QUANTITY) val quantityViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.CONTAINER_CODE) val containerCodeViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCodeViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.BIN_CODE_SOURCE) val binCodeSourceViolation: ViolationDto? = null,
    @param:JsonProperty(JsonFieldsProvider.BIN_CODE_TARGET) val binCodeTargetViolation: ViolationDto? = null
)

fun ViolationByFieldsDto.toViolationByField() = ViolationByFields(
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
    binCodeViolation = binCodeViolation?.toViolation(),
    binCodeTargetViolation = binCodeTargetViolation?.toViolation(),
    binCodeSourceViolation = binCodeSourceViolation?.toViolation()
)
