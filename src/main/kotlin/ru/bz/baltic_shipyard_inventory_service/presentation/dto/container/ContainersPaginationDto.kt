package ru.bz.baltic_shipyard_inventory_service.presentation.dto.container

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination


@Schema(description = "schema.containerspaginationdto.desc")
data class ContainersPaginationDto(
    @Schema(
        description = "schema.containers.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.CONTAINERS) val containers: List<ContainerDto>,

    @Schema(
        description = "schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1"
    )
    @JsonProperty(JsonFields.PAGE) val page: Int,

    @Schema(
        description = "schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "10"
    )
    @JsonProperty(JsonFields.PAGE_SIZE) val pageSize: Int
)

fun ContainersPagination.toContainersPaginationDto() = ContainersPaginationDto(
    containers = containers.map { it.toContainerDto() },
    page = page,
    pageSize = pageSize
)


