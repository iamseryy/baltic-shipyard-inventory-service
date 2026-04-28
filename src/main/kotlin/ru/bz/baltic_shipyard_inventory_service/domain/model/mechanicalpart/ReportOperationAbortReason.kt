package ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart

import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import java.time.LocalDateTime


data class ReportOperationAbortReason(
    val projectCode: String,
    val elementCode: String,
    val rootItemCode: String,
    val parentItemCode: String,
    val parentItemPosition: String,
    val parentOperationCode: Int,
    val childOperationCode: Int,
    val reportedQuantity: Int,
    val userLogin: String,
    val reportedDateTime: LocalDateTime,
    val abortReason: AbortReason?
)

fun ReportedOperation.toReportOperationAbortReason(abortReason: AbortReason?): ReportOperationAbortReason =
    ReportOperationAbortReason(
        projectCode = projectCode,
        elementCode = elementCode,
        rootItemCode = rootItemCode,
        parentItemCode = parentItemCode,
        parentItemPosition = parentItemPosition,
        parentOperationCode = parentOperationCode,
        childOperationCode = childOperationCode,
        reportedQuantity = reportedQuantity,
        userLogin = userLogin,
        reportedDateTime = reportedDateTime,
        abortReason = abortReason
    )
