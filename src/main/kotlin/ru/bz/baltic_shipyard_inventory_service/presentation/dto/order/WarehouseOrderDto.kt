package ru.bz.baltic_shipyard_inventory_service.presentation.dto.order

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.order.WarehouseOrder


@Schema(description = "schema.warehouse.order.desc")
@JsonInclude(JsonInclude.Include.ALWAYS)
data class WarehouseOrderDto(
    @Schema(
        description = "schema.origin.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "72"
    )
    @JsonProperty(JsonFields.ORIGIN) val origin: Int,

    @Schema(
        description = "schema.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "001001352"
    )
    @JsonProperty(JsonFields.CODE) val code: String,

    @Schema(
        description = "schema.set.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1"
    )
    @JsonProperty(JsonFields.SET) val set: Int,

    @Schema(
        description = "schema.line.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "2"
    )
    @JsonProperty(JsonFields.LINE) val line: Int,

    @Schema(
        description = "schema.sequence.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "2"
    )
    @JsonProperty(JsonFields.SEQUENCE) val sequence: Int
)

fun WarehouseOrder.toWarehouseOrderDto() = WarehouseOrderDto(
    origin = origin,
    code = code,
    set = set,
    line= line,
    sequence = sequence
)

fun WarehouseOrderDto.toWarehouseOrder() = WarehouseOrder(
    origin = origin,
    code = code,
    set = set,
    line= line,
    sequence = sequence
)
