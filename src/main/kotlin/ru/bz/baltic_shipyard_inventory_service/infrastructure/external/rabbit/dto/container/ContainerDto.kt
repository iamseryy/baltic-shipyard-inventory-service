package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStatus
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.LocationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.toLocation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.toLocationDto
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerDto(
    @JsonProperty(JsonFieldsProvider.CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_OWNER_CODE) val warehouseOwnerCode: String?,
    @JsonProperty(JsonFieldsProvider.DESCRIPTION) val description: String?,
    @JsonProperty(JsonFieldsProvider.STATUS) val status: ContainerStatus,
    @JsonProperty(JsonFieldsProvider.LOCATION) val location: LocationDto,
    @JsonProperty(JsonFieldsProvider.STOCK) val stock: ContainerStockDto,
    @JsonProperty(JsonFieldsProvider.USER_LOGIN_LAST_MODIFIED) val userLoginLastModified: String?,

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonProperty(JsonFieldsProvider.DATE_LAST_MODIFIED) val dateLastModified: LocalDateTime?
)

fun ContainerDto.toContainer() = Container(
    code = code,
    warehouseOwnerCode = warehouseOwnerCode ?: "",
    description = description,
    status = status,
    location = location.toLocation(),
    stock = stock.toContainerStock(),
    userLoginLastModified = userLoginLastModified ?: "",
    dateLastModified = dateLastModified ?: LocalDateTime.MIN
)

fun Container.toContainerDto() = ContainerDto(
    code = code,
    warehouseOwnerCode = warehouseOwnerCode,
    description = description,
    status = status,
    location = location.toLocationDto(),
    stock = stock.toContainerStockDto(),
    userLoginLastModified = userLoginLastModified,
    dateLastModified = dateLastModified
)