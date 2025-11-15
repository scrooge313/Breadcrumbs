package com.scrooge.breadcrumbs.baking.domain.state

import java.time.OffsetDateTime

data class BakingMetaData(
    val bakingId: Long,
    val name: String,
    val date: OffsetDateTime,
)
