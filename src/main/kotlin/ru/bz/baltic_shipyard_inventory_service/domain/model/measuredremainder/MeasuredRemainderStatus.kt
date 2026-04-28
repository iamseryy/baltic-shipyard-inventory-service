package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder

enum class MeasuredRemainderStatus(val number: Int, val description: String) {
    CREATED(1, "Введен"),
    IN_COTZ(2, "В КОЦе"),
    IN_MSCH(3, "В работе МСЧ"),
    USED(4, "Использован");

    companion object {
        fun getByNumber(number: Int) = entries.firstOrNull{ it.number == number }!!
    }
}
