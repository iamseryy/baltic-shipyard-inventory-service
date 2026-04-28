package ru.bz.baltic_shipyard_inventory_service.presentation.controller.transaction

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.toTransactionDto
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.transaction.TransactionUseCases


@Validated
@RestController
@RequestMapping("\${application.endpoint_v1.root}")
class TransactionControllerV1(
    private val transactionUseCases: TransactionUseCases
) {

    @GetMapping("/transaction/{id}")
    fun findTransactionById (@PathVariable("id") id: Int) = transactionUseCases.findById(id).toTransactionDto()


    @GetMapping("/transaction/update/{id}")
    fun updateTransactionById (@PathVariable("id") id: Int) = transactionUseCases.updateTransactionById(id).toTransactionDto()

}