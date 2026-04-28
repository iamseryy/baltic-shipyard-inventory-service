package ru.bz.baltic_shipyard_inventory_service.domain.model.filter

import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus


data class SearchMeasuredRemainderFilter (
    val id: String?,
    val remainderLike: String?,
    val project: String?,
    val materialLike: String?,
    val warehouse: String?,
    val location: String?,
    val status: MeasuredRemainderStatus?,
    val comment: String?,
    val lengthFrom: Double?,
    val widthFrom: Double?,
    val depth: Double?,
    val page: Int,
    val pageSize: Int
) {
    class EntryBuilder {
        private var id: String? = null
        private var remainderLike: String? = null
        private var project: String? = null
        private var materialLike: String? = null
        private var warehouse: String? = null
        private var location: String? = null
        private var status: MeasuredRemainderStatus? = null
        private var comment: String? = null
        private var lengthFrom: Double? = null
        private var widthFrom: Double? = null
        private var depth: Double? = null
        private var page: Int = 0
        private var pageSize: Int = 10

        fun id(id: String): EntryBuilder {
            this.id = id
            return this
        }

        fun remainderLike(remainderLike: String): EntryBuilder {
            this.remainderLike = remainderLike
            return this
        }

        fun project(project: String): EntryBuilder {
            this.project = project
            return this
        }

        fun materialLike(materialLike: String): EntryBuilder {
            this.materialLike = materialLike
            return this
        }

        fun warehouse(warehouse: String): EntryBuilder {
            this.warehouse = warehouse
            return this
        }

        fun location(location: String): EntryBuilder {
            this.location = location
            return this
        }

        fun status(status: MeasuredRemainderStatus): EntryBuilder {
            this.status = status
            return this
        }

        fun comment(comment: String): EntryBuilder {
            this.comment = comment
            return this
        }

        fun lengthFrom(lengthFrom: Double): EntryBuilder {
            this.lengthFrom = lengthFrom
            return this
        }

        fun widthFrom(widthFrom: Double): EntryBuilder {
            this.widthFrom = widthFrom
            return this
        }

        fun depth(depth: Double): EntryBuilder {
            this.depth = depth
            return this
        }

        fun page(page: Int): EntryBuilder {
            this.page = page
            return this
        }

        fun pageSize(pageSize: Int): EntryBuilder {
            this.pageSize = pageSize
            return this
        }

        fun build() = SearchMeasuredRemainderFilter(
            id = id,
            remainderLike = remainderLike,
            project = project,
            materialLike = materialLike,
            warehouse = warehouse,
            location = location,
            status = status,
            comment = comment,
            lengthFrom = lengthFrom,
            widthFrom = widthFrom,
            depth = depth,
            page = page,
            pageSize = pageSize
        )
    }
}