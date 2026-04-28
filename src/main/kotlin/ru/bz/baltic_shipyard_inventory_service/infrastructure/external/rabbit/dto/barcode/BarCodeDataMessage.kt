package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.barcode

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.RabbitMessage

@JsonInclude(JsonInclude.Include.ALWAYS)
data class BarCodeDataMessage(
    @JsonProperty(JsonFieldsProvider.OBJECT_DATA) val barCodeData: BarCodeDataDto?
): RabbitMessage(messageType = "BarCodeDataMessage")


