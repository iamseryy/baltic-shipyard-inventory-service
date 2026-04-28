package ru.bz.baltic_shipyard_inventory_service.domain.usecases.barcode

import org.springframework.stereotype.Component


@Component
data class BarCodeUseCases(
    val findDataByBarCode: UseCaseFindDataByBarCode
)
