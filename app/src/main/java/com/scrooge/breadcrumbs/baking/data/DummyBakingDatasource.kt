package com.scrooge.breadcrumbs.baking.data

import com.scrooge.breadcrumbs.baking.model.Baking
import java.time.OffsetDateTime

class DummyBakingDatasource {
    fun getBakings(): List<Baking> {
        return listOf(
            Baking(1, OffsetDateTime.now(), "Brioche"),
            Baking(2, OffsetDateTime.now().minusDays(1), "Sourdough"),
            *(3..20).map {
                Baking(it, OffsetDateTime.now().minusDays(2), "Sourdough")
            }.toTypedArray()
        )
    }
}