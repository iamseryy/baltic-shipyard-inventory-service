package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder

class MeasuredRemainders(
    var warehouseCode: String,
    var binCode: String,
    var measuredRemainders: List<MeasuredRemainders>
)