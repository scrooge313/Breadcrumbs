package com.scrooge.breadcrumbs.overview.data

import com.scrooge.breadcrumbs.overview.model.Baking
import java.time.OffsetDateTime

class DummyBakingDatasource {
    fun getBakings(): List<Baking> {
        return listOf(
            Baking(OffsetDateTime.now(), "Brioche"),
            Baking(OffsetDateTime.now().minusDays(1), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
            Baking(OffsetDateTime.now().minusDays(2), "Sourdough"),
        )
    }
}