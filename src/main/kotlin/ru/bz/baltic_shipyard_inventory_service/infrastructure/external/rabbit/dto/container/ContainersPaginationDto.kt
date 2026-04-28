package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainersPaginationDto(
    @JsonProperty(JsonFieldsProvider.CONTAINERS) val containers: List<ContainerDto>,
    @JsonProperty(JsonFieldsProvider.PAGE) val page: Int,
    @JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)

fun ContainersPaginationDto.toContainersPagination() = ContainersPagination(
    containers = containers.map { it.toContainer() },
    page = page,
    pageSize = pageSize
)

fun ContainersPagination.toContainersPaginationDto() = ContainersPaginationDto(
    containers = containers.map { it.toContainerDto()},
    page = page,
    pageSize = pageSize
)
