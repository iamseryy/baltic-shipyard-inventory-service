package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPageRequest


@Schema(description="schema.searchmeasuredremainderfilterdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderFilterDto(

    @Schema(
        description="schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R202011040000000009"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ID)
    val id: String? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.code.remainder.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="251-321"
    )
    @NotEmpty
    @JsonProperty(JsonFields.CODE_LIKE)
    val codeLike: String? = null,

    @Schema(
        description="schema.project.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="057120"
    )
    @NotEmpty
    @JsonProperty(JsonFields.PROJECT_CODE)
    val projectCode: String? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.material.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0421\u04423\u0441\u043f2"
    )
    @NotEmpty
    @JsonProperty(JsonFields.MATERIAL_LIKE)
    val materialLike: String? = null,

    @Schema(
        description="schema.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0200"
    )
    @NotEmpty
    @JsonProperty(JsonFields.WAREHOUSE_CODE)
    val warehouseCode: String? = null,

    @Schema(
        description="schema.bin.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="01"
    )
    @NotEmpty
    @JsonProperty(JsonFields.BIN_CODE)
    val binCode: String? = null,

    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        allowableValues = ["CREATED", "IN_COTZ", "IN_MSCH", "USED", "ON_CONSIGNMENT", "DELETED"],
        type = "String",
        example="USED"
    )
//    @EnumTypeSubset(
//        anyOf = [MeasuredRemainderStatus.CREATED, MeasuredRemainderStatus.IN_COTZ, MeasuredRemainderStatus.IN_MSCH],
//        message = "code={error.1009.code};description={error.1009.description} CREATED, IN_COTZ, IN_MSCH, USED, ON_CONSIGNMENT, DELETED"
//    )
//    @ValidEnumWithAllowed(
//        allowedValues = ["CREATED", "IN_COTZ", "IN_MSCH", "USED", "ON_CONSIGNMENT", "DELETED"],
//        message = "code={error.1009.code};description={error.1009.description} CREATED, IN_COTZ, IN_MSCH, USED, ON_CONSIGNMENT, DELETED"
//    )
    @JsonProperty(JsonFields.STATUS)
    val status: MeasuredRemainderStatus? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.length.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="22.2"
    )
    @get:DecimalMin(message = "code={error.1002.code};description={error.1002.description}", value = "0")
    @get:DecimalMax(message = "code={error.1004.code};description={error.1004.description}", value = "1000000000")
    @JsonProperty(JsonFields.LENGTH_FROM)
    val lengthFrom: Double? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.width.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="30"
    )
    @get:DecimalMin(message = "code={error.1002.code};description={error.1002.description}", value = "0")
    @get:DecimalMax(message = "code={error.1004.code};description={error.1004.description}", value = "1000000000")
    @JsonProperty(JsonFields.WIDTH_FROM)
    val widthFrom: Double? = null,

    @Schema(
        description="schema.measuredremainder.depth.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="15"
    )
    @get:DecimalMin(message = "code={error.1002.code};description={error.1002.description}", value = "0")
    @get:DecimalMax(message = "code={error.1004.code};description={error.1004.description}", value = "1000000000")
    @JsonProperty(JsonFields.THICKNESS_FROM)
    val thicknessFrom: Double? = null,

    @Schema(
        description="schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="11"
    )
    @get:Min(message = "code={error.1003.code};description={error.1003.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.PAGE_NUMBER)
    val pageNumber: Int = 1,

    @Schema(
        description="schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="10"
    )
    @get:Min(message = "code={error.1003.code};description={error.1003.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.PAGE_SIZE)
    val pageSize: Int = 10
)

fun MeasuredRemainderFilterDto.toMeasuredRemainderFilter() = MeasuredRemainderFilter (
    id = id,
    codeLike = codeLike,
    projectCode = projectCode,
    materialLike = materialLike,
    warehouseCode = warehouseCode,
    binCode = binCode,
    status = status,
    lengthFrom = lengthFrom,
    widthFrom = widthFrom,
    thicknessFrom = thicknessFrom
)

fun MeasuredRemainderFilterDto.toDomainPageRequest() = DomainPageRequest(
    number = pageNumber - 1,
    size = pageSize
)