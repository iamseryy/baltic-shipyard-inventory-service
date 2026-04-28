package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.mechanicalpart

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ReportedOperationDto(
    @field:JsonProperty(JsonFieldsProvider.PROJECT_CODE) val projectCode: String,
    @field:JsonProperty(JsonFieldsProvider.ELEMENT_CODE) val elementCode: String,
    @field:JsonProperty(JsonFieldsProvider.ROOT_ITEM_CODE) val rootItemCode: String,
    @field:JsonProperty(JsonFieldsProvider.PARENT_ITEM_CODE) val parentItemCode: String,
    @field:JsonProperty(JsonFieldsProvider.PARENT_ITEM_POSITION) val parentItemPosition: String,
    @field:JsonProperty(JsonFieldsProvider.PARENT_OPERATION_CODE) val parentOperationCode: Int,
    @field:JsonProperty(JsonFieldsProvider.CHILD_OPERATION_CODE) val childOperationCode: Int,
    @field:JsonProperty(JsonFieldsProvider.REPORTED_QUANTITY) val reportedQuantity: Int,
    @field:JsonProperty(JsonFieldsProvider.USER_LOGIN) val userLogin: String,

//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
//    @JsonSerialize(using = LocalDateTimeSerializer::class)
//    @JsonProperty(JsonFieldsProvider.REPORTED_DATE_TIME) val reportedDateTime: LocalDateTime
)

fun ReportedOperation.toReportedOperationDto(): ReportedOperationDto = ReportedOperationDto(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = rootItemCode,
    parentItemCode = parentItemCode,
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity,
    userLogin = userLogin,
//    reportedDateTime = reportedDateTime
)

fun ReportedOperationDto.toReportedOperation(): ReportedOperation = ReportedOperation(
    projectCode = projectCode,
    elementCode = elementCode,
    rootItemCode = rootItemCode,
    parentItemCode = parentItemCode,
    parentItemPosition = parentItemPosition,
    parentOperationCode = parentOperationCode,
    childOperationCode = childOperationCode,
    reportedQuantity = reportedQuantity,
    userLogin = userLogin,
    reportedDateTime = LocalDateTime.now(),
)