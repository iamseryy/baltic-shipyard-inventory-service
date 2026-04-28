package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder

interface MeasuredRemainderBase {
    val measuredRemainderId: String
    val remainder: String
    val project: String
    val material: String
    val warehouse: String
    val location: String
    val sequence: Int
    val status: MeasuredRemainderStatus
    val comment: String
    val length: Double
    val width: Double
    val depth: Double
}