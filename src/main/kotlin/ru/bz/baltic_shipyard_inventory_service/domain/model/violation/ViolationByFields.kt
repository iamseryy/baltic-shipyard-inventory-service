package ru.bz.baltic_shipyard_inventory_service.domain.model.violation

import java.time.LocalDateTime


data class ViolationByFields(
    val remainderViolation: Violation? = null,
    val projectViolation: Violation? = null,
    val materialViolation: Violation? = null,
    val warehouseViolation: Violation? = null,
    val warehouseCodeViolation: Violation? = null,
    val locationViolation: Violation? = null,
    val binCodeViolation: Violation? = null,
    val binCodeTargetViolation: Violation? = null,
    val binCodeSourceViolation: Violation? = null,
    val locationSourceViolation: Violation? = null,
    val locationTargetViolation: Violation? = null,
    val sequenceViolation: Violation? = null,
    val statusViolation: Violation? = null,
    val commentViolation: Violation? = null,
    val lengthViolation: Violation? = null,
    val widthViolation: Violation? = null,
    val depthViolation: Violation? = null,
    val itemViolation: Violation? = null,
    val itemCodeViolation: Violation? = null,
    val lotViolation: Violation? = null,
    val lotCodeViolation: Violation? = null,
    val quantityViolation: Violation? = null,
    val containerCodeViolation: Violation? = null,
    val projectCodeViolation: Violation? = null,
    val elementCodeViolation: Violation? = null,
    val rootItemCodeViolation: Violation? = null,
    val parentItemCodeViolation: Violation? = null,
    val parentItemPositionViolation: Violation? = null,
    val parentOperationCodeViolation: Violation? = null,
    val childOperationCodeViolation: Violation? = null,
    val reportedQuantityViolation: Violation? = null,
) {
    class EntryBuilder {
        private var remainderViolation: Violation? = null
        private var projectViolation: Violation? = null
        private var materialViolation: Violation? = null
        private var warehouseViolation: Violation? = null
        private var warehouseCodeViolation: Violation? = null
        private var locationViolation: Violation? = null
        private var binCodeViolation: Violation? = null
        private var binCodeTargetViolation: Violation? = null
        private var binCodeSourceViolation: Violation? = null
        private var locationSourceViolation: Violation? = null
        private var locationTargetViolation: Violation? = null
        private var sequenceViolation: Violation? = null
        private var statusViolation: Violation? = null
        private var commentViolation: Violation? = null
        private var lengthViolation: Violation? = null
        private var widthViolation: Violation? = null
        private var depthViolation: Violation? = null
        private var itemViolation: Violation? = null
        private var itemCodeViolation: Violation? = null
        private var lotViolation: Violation? = null
        private var lotCodeViolation: Violation? = null
        private var quantityViolation: Violation? = null
        private var containerCodeViolation: Violation? = null
        private var projectCodeViolation: Violation? = null
        private var elementCodeViolation: Violation? = null
        private var rootItemCodeViolation: Violation? = null
        private var parentItemCodeViolation: Violation? = null
        private var parentItemPositionViolation: Violation? = null
        private var parentOperationCodeViolation: Violation? = null
        private var childOperationCodeViolation: Violation? = null
        private var reportedQuantityViolation: Violation? = null


        fun projectCodeViolation(violation: Violation): EntryBuilder {
            this.projectCodeViolation = violation
            return this
        }

        fun elementCodeViolation(violation: Violation): EntryBuilder {
            this.elementCodeViolation = violation
            return this
        }

        fun rootItemCodeViolation(violation: Violation): EntryBuilder {
            this.rootItemCodeViolation = violation
            return this
        }

        fun parentItemCodeViolation(violation: Violation): EntryBuilder {
            this.parentItemCodeViolation = violation
            return this
        }

        fun parentItemPositionViolation(violation: Violation): EntryBuilder {
            this.parentItemPositionViolation = violation
            return this
        }

        fun parentOperationCodeViolation(violation: Violation): EntryBuilder {
            this.parentOperationCodeViolation = violation
            return this
        }

        fun childOperationCodeViolation(violation: Violation): EntryBuilder {
            this.childOperationCodeViolation = violation
            return this
        }

        fun reportedQuantityViolation(violation: Violation): EntryBuilder {
            this.reportedQuantityViolation = violation
            return this
        }

        fun projectViolation(violation: Violation): EntryBuilder {
            this.projectViolation = violation
            return this
        }

        fun materialViolation(violation: Violation): EntryBuilder {
            this.materialViolation = violation
            return this
        }

        fun warehouseViolation(violation: Violation): EntryBuilder {
            this.warehouseViolation = violation
            return this
        }

        fun warehouseCodeViolation(violation: Violation): EntryBuilder {
            this.warehouseCodeViolation = violation
            return this
        }

        fun locationViolation(violation: Violation): EntryBuilder {
            this.locationViolation = violation
            return this
        }

        fun binCodeViolation(violation: Violation): EntryBuilder {
            this.binCodeViolation = violation
            return this
        }

        fun binCodeSourceViolation(violation: Violation): EntryBuilder {
            this.binCodeSourceViolation = violation
            return this
        }

        fun binCodeTargetViolation(violation: Violation): EntryBuilder {
            this.binCodeTargetViolation = violation
            return this
        }


        fun locationSourceViolation(violation: Violation): EntryBuilder {
            this.locationSourceViolation = violation
            return this
        }

        fun locationTargetViolation(violation: Violation): EntryBuilder {
            this.locationTargetViolation = violation
            return this
        }

        fun sequenceViolation(violation: Violation): EntryBuilder {
            this.sequenceViolation = violation
            return this
        }

        fun statusViolation(violation: Violation): EntryBuilder {
            this.statusViolation = violation
            return this
        }

        fun commentViolation(violation: Violation): EntryBuilder {
            this.commentViolation = violation
            return this
        }

        fun widthViolation(violation: Violation): EntryBuilder {
            this.widthViolation = violation
            return this
        }

        fun depthViolation(violation: Violation): EntryBuilder {
            this.depthViolation = violation
            return this
        }

        fun lengthViolation(violation: Violation): EntryBuilder {
            this.lengthViolation = violation
            return this
        }

        fun itemViolation(violation: Violation): EntryBuilder {
            this.itemViolation = violation
            return this
        }

        fun itemCodeViolation(violation: Violation): EntryBuilder {
            this.itemCodeViolation = violation
            return this
        }

        fun lotViolation(violation: Violation): EntryBuilder {
            this.lotViolation = violation
            return this
        }

        fun lotCodeViolation(violation: Violation): EntryBuilder {
            this.lotCodeViolation = violation
            return this
        }

        fun quantityViolation(violation: Violation): EntryBuilder {
            this.quantityViolation = violation
            return this
        }

        fun containerCodeViolation(violation: Violation): EntryBuilder {
            this.containerCodeViolation = violation
            return this
        }

        fun build() = ViolationByFields(
            remainderViolation = remainderViolation,
            projectViolation = projectViolation,
            materialViolation = materialViolation,
            warehouseViolation = warehouseViolation,
            warehouseCodeViolation = warehouseCodeViolation,
            locationViolation = locationViolation,
            binCodeViolation = binCodeViolation,
            binCodeTargetViolation = binCodeTargetViolation,
            binCodeSourceViolation = binCodeSourceViolation,
            locationSourceViolation = locationSourceViolation,
            locationTargetViolation = locationTargetViolation,
            sequenceViolation = sequenceViolation,
            statusViolation = statusViolation,
            commentViolation = commentViolation,
            widthViolation = widthViolation,
            depthViolation = depthViolation,
            lengthViolation = lengthViolation,
            itemViolation = itemViolation,
            itemCodeViolation = itemCodeViolation,
            lotViolation  = lotViolation,
            lotCodeViolation  = lotCodeViolation,
            quantityViolation = quantityViolation,
            containerCodeViolation = containerCodeViolation,
            projectCodeViolation = projectCodeViolation,
            elementCodeViolation = elementCodeViolation,
            rootItemCodeViolation = rootItemCodeViolation,
            parentItemCodeViolation = parentItemCodeViolation,
            parentItemPositionViolation = parentItemPositionViolation,
            parentOperationCodeViolation = parentOperationCodeViolation,
            childOperationCodeViolation = childOperationCodeViolation,
            reportedQuantityViolation = reportedQuantityViolation
        )

        fun violationExists() = remainderViolation != null || projectViolation != null || materialViolation != null ||
                warehouseViolation != null || warehouseCodeViolation != null || locationViolation != null || binCodeViolation != null ||
                binCodeTargetViolation != null || binCodeSourceViolation != null || locationSourceViolation != null ||
                locationTargetViolation != null || sequenceViolation != null || statusViolation != null ||
                commentViolation != null || widthViolation != null || depthViolation != null || lengthViolation != null ||
                itemViolation != null || itemCodeViolation != null || lotViolation != null || lotCodeViolation != null ||
                quantityViolation != null || containerCodeViolation != null || projectCodeViolation != null || elementCodeViolation != null ||
                rootItemCodeViolation != null || parentItemCodeViolation != null || parentItemPositionViolation != null || parentOperationCodeViolation != null ||
                childOperationCodeViolation != null || reportedQuantityViolation != null
    }

    fun violationExists() = remainderViolation != null || projectViolation != null || materialViolation != null ||
            warehouseViolation != null || warehouseCodeViolation != null || locationViolation != null ||
            binCodeViolation != null || binCodeTargetViolation != null || binCodeSourceViolation != null ||
            locationSourceViolation != null || locationTargetViolation != null || sequenceViolation != null || statusViolation != null ||
            commentViolation != null || widthViolation != null || depthViolation != null || lengthViolation != null ||
            itemViolation != null || itemCodeViolation != null || lotViolation != null || lotCodeViolation != null ||
            quantityViolation != null || containerCodeViolation != null || projectCodeViolation != null || elementCodeViolation != null ||
            rootItemCodeViolation != null || parentItemCodeViolation != null || parentItemPositionViolation != null || parentOperationCodeViolation != null ||
            childOperationCodeViolation != null || reportedQuantityViolation != null
}


