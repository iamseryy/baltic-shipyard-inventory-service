package ru.bz.baltic_shipyard_inventory_service.presentation.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStock
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.warehouse.LocationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.warehouse.toLocation
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.warehouse.toLocationDto
import java.time.LocalDateTime


@Schema(description = "schema.container.desc")
@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerDto(
    @Schema(
        description = "schema.container.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R020000013"
    )
    @JsonProperty(JsonFields.CODE) val code: String,

    @Schema(
        description = "schema.warehouse.owner.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R0200"
    )
    @JsonProperty(JsonFields.WAREHOUSE_OWNER_CODE) val warehouseOwnerCode: String,

    @Schema(
        description = "schema.container.desc.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "for spool card"
    )
    @JsonProperty(JsonFields.DESCRIPTION) val description: String?,

    @Schema(
        description = "schema.container.status.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "ON_CONFIG"
    )
    @JsonProperty(JsonFields.STATUS) val status: ContainerStatus,

    @Schema(
        description = "schema.container.location.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
    )
    @JsonProperty(JsonFields.LOCATION) val location: LocationDto,

    @Schema(
        description = "schema.container.stock.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
    )
    @JsonProperty(JsonFields.STOCK) val stock: List<ContainerStockPositionDto>,

    @Schema(
        description = "schema.container.lastmodifieduser.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "user"
    )
    @JsonProperty(JsonFields.LAST_MODIFIED_USER) val userLoginLastModified: String,

    @Schema(
        description = "schema.container.lastmodifieddate.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "string",
        example="2024-12-26T09:00:48.169662"
    )
    @JsonProperty(JsonFields.LAST_MODIFIED_DATE) val dateLastModified: LocalDateTime

)

fun Container.toContainerDto() = ContainerDto(
    code = code,
    warehouseOwnerCode = warehouseOwnerCode,
    description = description,
    status = status,
    location = location.toLocationDto(),
    stock = stock.containerStock.map { it.toContainerStockPositionDto()},
    userLoginLastModified = userLoginLastModified,
    dateLastModified = dateLastModified
)

fun ContainerDto.toContainer() = Container(
    code = code,
    warehouseOwnerCode = warehouseOwnerCode,
    description = description,
    status = status,
    location = location.toLocation(),
    stock = ContainerStock(stock.map { it.toContainerStockPosition() }),
    userLoginLastModified = userLoginLastModified,
    dateLastModified = dateLastModified
)