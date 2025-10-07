package com.scrooge.breadcrumbs.baking.model

import java.time.OffsetDateTime

typealias BakingId = Int

data class Baking(
    val id: BakingId,
    val date: OffsetDateTime,
    val type: String,
)