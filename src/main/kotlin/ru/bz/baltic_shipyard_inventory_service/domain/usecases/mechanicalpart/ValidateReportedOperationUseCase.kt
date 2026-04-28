package ru.bz.baltic_shipyard_inventory_service.domain.usecases.mechanicalpart

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportOperationAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.toReportOperationAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields


@Component
class ValidateReportedOperationUseCase {
    operator fun invoke(reportedOperation: ReportedOperation): ReportOperationAbortReason =
        buildViolationByFields(reportedOperation).let { violationByFields ->
            if (violationByFields.violationExists()) AbortReason(violationByFields, null) else null
        }.let { abortReason ->
            reportedOperation.toReportOperationAbortReason(abortReason)
        }


    private fun buildViolationByFields(reportedOperation: ReportedOperation): ViolationByFields =
        ViolationByFields.EntryBuilder()
            .let { builder ->
                projectCodeViolationToBuilder(builder, reportedOperation)
            }.let { builder ->
                elementCodeViolationToBuilder(builder, reportedOperation)
            }.let { builder ->
                rootItemCodeViolationToBuilder(builder, reportedOperation)
            }.let { builder ->
                parentItemCodeViolationToBuilder(builder, reportedOperation)
            }.let { builder ->
                parentItemPositionViolationToBuilder(builder, reportedOperation)
            }.let { builder ->
                parentOperationCodeViolationToBuilder(builder, reportedOperation)
            }.let { builder ->
                childOperationCodeViolationToBuilder(builder, reportedOperation)
            }.let { builder ->
                reportQuantityViolationToBuilder(builder, reportedOperation)
            }.build()



    private fun projectCodeViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }

    private fun elementCodeViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }

    private fun rootItemCodeViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }

    private fun parentItemCodeViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }

    private fun parentItemPositionViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }

    private fun parentOperationCodeViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }

    private fun childOperationCodeViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }

    private fun reportQuantityViolationToBuilder(
        builder: ViolationByFields.EntryBuilder,
        reportedOperation: ReportedOperation
    ): ViolationByFields.EntryBuilder {


        return builder
    }


}