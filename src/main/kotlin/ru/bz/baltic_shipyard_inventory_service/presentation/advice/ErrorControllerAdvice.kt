package ru.bz.baltic_shipyard_inventory_service.presentation.advice

import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.LocaleResolver
import ru.bz.baltic_shipyard_inventory_service.common.errors.ExternalServiceTimeoutException
import ru.bz.baltic_shipyard_inventory_service.common.errors.ExternalServiceUnavailableException
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.Violation
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.transfer.TransferItemByLocationsAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.container.SearchContainerFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchChildrenInventoryBalanceFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchContainerFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchInventoryBalanceFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter.SearchTransactionFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory.SearchChildrenInventoryBalanceFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory.SearchInventoryBalanceFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.InventoriedMeasuredRemaindersDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.MeasuredRemainderUpdateDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.filter.MeasuredRemainderFilterDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderFilterAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderFilterValidationResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderFilterViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderUpdateAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderUpdateValidationResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation.MeasuredRemainderUpdateViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.SearchTransactionFilterValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.TransferItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.toTransferItemByLocationsValidationErrorResponse
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.InventoriedMeasuredRemainderViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.InventoriedMeasuredRemaindersViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.transfer.TransferItemByLocationsViolationByFieldDto


private const val REGEX_PATTERN_FOR_SUBSTRING_INDEX = "\\[(\\d+)\\]"
private const val REGEX_PATTERN_FOR_SUBSTRING_FIELD = "^[^.]+\\."



@ControllerAdvice
class ErrorControllerAdvice(
    private val messageSource: ReloadableResourceBundleMessageSource,
    private val localeResolver: LocaleResolver
) {
    private val logger = KotlinLogging.logger {}


    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun onMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ) = when (exception.target) {
        is MeasuredRemainderFilterDto -> MeasuredRemainderFilterValidationResponse(
            filter = exception.target as MeasuredRemainderFilterDto,
            abortReasonDto = MeasuredRemainderFilterAbortReasonDto(
                violationsByFields = MeasuredRemainderFilterViolationDto.EntryBuilder().build(exception.bindingResult.fieldErrors)
            )
        )
        is MeasuredRemainderUpdateDto -> MeasuredRemainderUpdateValidationResponse(
            id = extractIdFromUri(request.requestURI),
            updateDto = exception.target as MeasuredRemainderUpdateDto,
            abortReasonDto = MeasuredRemainderUpdateAbortReasonDto(
                violationsByFields = MeasuredRemainderUpdateViolationDto.EntryBuilder().build(exception.bindingResult.fieldErrors)
            )
        )
//            is MeasuredRemainderUpdateDto ->
//                MeasuredRemainderForUpdateViolationByFieldDto.EntryBuilder().build(exception.bindingResult.fieldErrors).let {
//                    (exception.target as MeasuredRemainderUpdateDto).toMeasuredRemainderForUpdateValidationErrorResponse(
//                        AbortReasonDto(violationsByFields = it)
//                    )
//                }

            is SearchTransactionFilterDto -> SearchTransactionFilterValidationErrorResponse.EntryBuilder().build(exception.bindingResult.fieldErrors)
//            is MeasuredRemainderUpdateDto ->
//                MeasuredRemainderForUpdateViolationByFieldDto.EntryBuilder().build(exception.bindingResult.fieldErrors).let {
//                    (exception.target as MeasuredRemainderUpdateDto).toMeasuredRemainderForUpdateValidationErrorResponse(
//                        AbortReasonDto(violationsByFields = it)
//                    )
//                }
            is InventoriedMeasuredRemaindersDto -> {
                val regexIndex = Regex(REGEX_PATTERN_FOR_SUBSTRING_INDEX)
                val regexField = Regex(REGEX_PATTERN_FOR_SUBSTRING_FIELD)

                val headerFieldErrors = exception.bindingResult.fieldErrors.filter { !regexIndex.containsMatchIn(it.field)  }
                val measuredRemaindersFieldError = exception.bindingResult.fieldErrors.filter { regexIndex.containsMatchIn(it.field)  }.sortedBy {   regexIndex.find(it.field)?.groupValues?.get(1)?.toInt() }

                val measuredRemaindersFieldErrorGrouping = measuredRemaindersFieldError.groupBy {
                    regexIndex.find(it.field)?.groupValues?.get(1)?.toInt()
                }.toMutableMap()

                (exception.target as InventoriedMeasuredRemaindersDto).measuredRemainders.forEachIndexed{ index, inventoriedMeasuredRemainderDto ->
                    if(measuredRemaindersFieldErrorGrouping[index].isNullOrEmpty()) {
                        measuredRemaindersFieldErrorGrouping[index] to null
                    }
                    }



                val test1 = InventoriedMeasuredRemaindersViolationByFieldDto.EntryBuilder().build(headerFieldErrors)

                val test2 = measuredRemaindersFieldErrorGrouping.map {
                    InventoriedMeasuredRemainderViolationByFieldDto.EntryBuilder().build(
                        it.value.map {fieldError ->
                            FieldError(
                                fieldError.objectName,
                                fieldError.field.replace(regexField, ""),
                                fieldError.defaultMessage ?: ""
                            )
                        })
                }


                val test3 = measuredRemaindersFieldErrorGrouping.map { (key, value) ->
                    (exception.target as InventoriedMeasuredRemaindersDto).measuredRemainders[key!!] to
                            InventoriedMeasuredRemainderViolationByFieldDto.EntryBuilder().build(
                                value.map {fieldError ->
                                    FieldError(
                                        fieldError.objectName,
                                        fieldError.field.replace(regexField, ""),
                                        fieldError.defaultMessage ?: ""
                                    )
                                }
                            )


                }.toMap()

    //            val test4 = (exception.target as InventoriedMeasuredRemaindersDto)
    //                .toInventoriedMeasuredRemaindersValidationErrorResponse(
    //                    abortReasonDtoHeader = AbortReasonDto(violationsByFields = test1),
    //                    abortReasonDtoLines = test2.map { AbortReasonDto(violationsByFields = it) }
    //                )

    //
    //            test3..toInventoriedMeasuredRemaindersValidationErrorResponse(
    //                abortReasonDtoHeader = AbortReasonDto(violationsByFields = test1),
    //                abortReasonDtoLines = test3.values.map { AbortReasonDto(violationsByFields = it)}
    //            )

    //            (exception.target as InventoriedMeasuredRemaindersDto)
    //                .toInventoriedMeasuredRemaindersValidationErrorResponse(
    //                abortReasonDtoHeader = AbortReasonDto(violationsByFields = test1),
    //                abortReasonDtoLines = test2.map { AbortReasonDto(violationsByFields = it) }
    //            )


              //  test1

    //
    //            InventoriedMeasuredRemaindersValidationErrorResponse(
    //
    //            )
            }

            //InventoriedMeasuredRemaindersValidationErrorResponse.EntryBuilder().build(exception.bindingResult)


            is TransferItemByLocationsDto -> TransferItemByLocationsViolationByFieldDto.EntryBuilder()
                .build(exception.bindingResult.fieldErrors)
                .let {
                (exception.target as TransferItemByLocationsDto).toTransferItemByLocationsValidationErrorResponse(
                    TransferItemByLocationsAbortReasonDto(violationsByFields = it)
                )
            }

            is SearchInventoryBalanceFilterDto -> SearchInventoryBalanceFilterValidationErrorResponse.EntryBuilder().build(exception.bindingResult.fieldErrors)
            is SearchChildrenInventoryBalanceFilterDto -> SearchChildrenInventoryBalanceFilterValidationErrorResponse.EntryBuilder().build(exception.bindingResult.fieldErrors)
            is SearchContainerFilterDto -> SearchContainerFilterValidationErrorResponse.EntryBuilder().build(exception.fieldErrors)
            else -> MeasuredRemainderFilterViolationDto.EntryBuilder().build(exception.bindingResult.fieldErrors)
    }

//    @ExceptionHandler(HttpMessageNotReadableException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    fun onMessageNotReadableException(
//        exception: HttpMessageNotReadableException,
//        request: HttpServletRequest
//    ) = Violation(
//        messageSource.getMessage("error.400.code", arrayOf(), localeResolver.resolveLocale(request)),
//        exception.localizedMessage
//    )

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun onMessageNotReadableException(
        exception: HttpMessageNotReadableException
    ): ErrorResponse {
        logger.warn { "${exception.message}" }
        return ErrorResponse(
            code = HttpStatus.BAD_REQUEST.value().toString(),
            description = exception.localizedMessage
        )

    }


    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleResourceNotFoundException(
        exception: ResourceNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {
        logger.warn { "${exception.message}" }
        return ErrorResponse(
            code = exception.errorCode,
            description = messageSource.getMessage(
                exception.errorCode,
                emptyArray(),
                localeResolver.resolveLocale(request)
            )
        )
    }

    @ExceptionHandler(ExternalServiceTimeoutException::class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ResponseBody
    fun handleTimeoutException(
        exception: ExternalServiceTimeoutException,
        request: HttpServletRequest
    ): ErrorResponse {
        logger.warn { "External service timeout: ${exception.message}" }
        return ErrorResponse(
            code = exception.errorCode,
            description = messageSource.getMessage(
                exception.errorCode,
                emptyArray(),
                localeResolver.resolveLocale(request)
            )
        )
    }

    @ExceptionHandler(ExternalServiceUnavailableException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    fun handleUnavailableException(
        exception: ExternalServiceUnavailableException,
        request: HttpServletRequest
    ): ErrorResponse {
        logger.warn { "External service unavailable: ${exception.message}" }
        return ErrorResponse(
            code = exception.errorCode,
            description = messageSource.getMessage(
                exception.errorCode,
                emptyArray(),
                localeResolver.resolveLocale(request)
            )
        )
    }

//    ErrorResponse(
//        code = exception.errorCode,
//        description = messageSource.getMessage(exception.errorCode, emptyArray(), localeResolver.resolveLocale(request)
//    )



//    @ExceptionHandler(AuthenticationException::class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ResponseBody
//    fun onAccessDeniedException(
//        exception: AuthenticationException,
//        request: HttpServletRequest
//    ) = "test"

//        Violation (
//        messageSource.getMessage("error.404.code", arrayOf(), localeResolver.resolveLocale(request)),
//        messageSource.getMessage("error.404.description", arrayOf(), localeResolver.resolveLocale(request))
//    )


    private fun extractIdFromUri(uri: String): String {
        // Убираем завершающий слеш, если есть
        val cleanUri = uri.trimEnd('/')
        val parts = cleanUri.split("/")

        // Проверяем, что:
        // 1. В URI достаточно сегментов (минимум 3: [..., "measured-remainders", "id"])
        // 2. Предпоследний сегмент — "measured-remainders"
        if (parts.size >= 3 && parts[parts.size - 2] == "measured-remainders") {
            return parts.last() // последний сегмент — это id
        }
        return "unknown"
    }
}