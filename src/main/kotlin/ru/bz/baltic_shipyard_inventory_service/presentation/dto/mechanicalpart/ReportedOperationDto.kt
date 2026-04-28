package ru.bz.baltic_shipyard_inventory_service.presentation.dto.mechanicalpart

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportOperationAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import java.time.LocalDateTime


@Schema(description = "schema.reported.operation.desc")
data class ReportedOperationDto(
    @Schema(
        description = "schema.project.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "057120"
    )
    @NotEmpty
    @JsonProperty(JsonFields.PROJECT) val projectCode: String,

    @Schema(
        description = "schema.element.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "057120V"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ELEMENT) val elementCode: String,

    @Schema(
        description = "schema.root.item.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "3970001108002301"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ROOT_ITEM) val rootItemCode: String,

    @Schema(
        description = "schema.master.item.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "SUB15112"
    )
    @NotEmpty
    @JsonProperty(JsonFields.MASTER_ITEM) val parentItemCode: String,

    @Schema(
        description = "schema.master.item.position.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1.2"
    )
    @NotEmpty
    @JsonProperty(JsonFields.MASTER_ITEM_POSITION) val parentItemPosition: String,

    @Schema(
        description = "schema.master.operation.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "10"
    )
    @get:Min(message = "code={error.1003.code};description={error.1002.description}", value = 0)
    @get:Max(message = "code={error.1010.code};description={error.1010.description}", value = 1_000)
    @JsonProperty(JsonFields.MASTER_OPERATION) val parentOperationCode: Int,

    @Schema(
        description = "schema.operation.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1005"
    )
    @get:Min(message = "code={error.1003.code};description={error.1003.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.OPERATION) val childOperationCode: Int,


    @Schema(
        description = "schema.operation.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1005"
    )
    @get:Min(message = "code={error.1003.code};description={error.1003.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.QUANTITY) val reportedQuantity: Int,

    @Schema(
        description="schema.abortreasondto.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
    )
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: ReportedOperationAbortReasonDto? = null
)

fun ReportedOperationDto.toReportedOperation(userLogin: String): ReportedOperation = ReportedOperation(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = rootItemCode,
    parentItemCode = parentItemCode,
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity,
    userLogin = userLogin,
    reportedDateTime = LocalDateTime.now()
)

fun ReportOperationAbortReason.toReportedOperationDto() = ReportedOperationDto(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = rootItemCode,
    parentItemCode = parentItemCode,
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity,
    abortReason = abortReason?.toReportedOperationAbortReasonDto()

)


fun ReportedOperation.toReportedOperationDto(): ReportedOperationDto = ReportedOperationDto(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = rootItemCode,
    parentItemCode = parentItemCode,
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity
)
