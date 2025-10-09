package com.scrooge.breadcrumbs.baking.data

import com.scrooge.breadcrumbs.baking.model.Baking
import com.scrooge.breadcrumbs.baking.model.BakingId
import java.time.OffsetDateTime

class DummyBakingDatasource {
    private var bakings = listOf(
        Baking(1, OffsetDateTime.now(), "Brioche"),
        Baking(2, OffsetDateTime.now().minusDays(1), "Sourdough"),
        *(3..20).map {
            Baking(it, OffsetDateTime.now().minusDays(2), "Sourdough")
        }.toTypedArray()
    )

    fun getBakings() = bakings

    fun getBaking(bakingId: BakingId) =
        bakings.first { it.id == bakingId }

//    todo when using auto DI with singleton datasource
//    fun deleteBaking(bakingId: BakingId) {
//        bakings = bakings.filter { it.id != bakingId }
//    }
}