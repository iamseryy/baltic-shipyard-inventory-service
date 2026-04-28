package ru.bz.baltic_shipyard_inventory_service.domain.model.container

import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Location
import java.time.LocalDateTime


data class Container(
    val code: String,
    val warehouseOwnerCode: String,
    val description: String?,
    val status: ContainerStatus,
    val location: Location,
    val stock: ContainerStock,
    val userLoginLastModified: String,
    val dateLastModified: LocalDateTime
)
