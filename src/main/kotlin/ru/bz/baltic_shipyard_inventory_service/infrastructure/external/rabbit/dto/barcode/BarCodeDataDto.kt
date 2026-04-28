package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.barcode

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.barcode.BarCodeData
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class BarCodeDataDto(
    @JsonProperty(JsonFieldsProvider.DATA) val barCodeData: Map<String, Any>
)

fun BarCodeDataDto.toBarCodeData() = BarCodeData(
    barCodeData = barCodeData
)