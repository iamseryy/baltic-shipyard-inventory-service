package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.filter

import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus

data class MeasuredRemainderFilter (
    val id: String? = null,
    val codeLike: String? = null,
    val projectCode: String? = null,
    val materialLike: String? = null,
    val warehouseCode: String? = null,
    val binCode: String? = null,
    val status: MeasuredRemainderStatus? = null,
    val lengthFrom: Double? = null,
    val widthFrom: Double? = null,
    val thicknessFrom: Double? = null
)