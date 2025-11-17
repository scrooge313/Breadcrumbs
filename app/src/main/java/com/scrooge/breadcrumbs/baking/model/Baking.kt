package com.scrooge.breadcrumbs.baking.model

import java.time.OffsetDateTime

typealias BakingId = Long

data class Baking(
    val id: BakingId,
    val date: OffsetDateTime,
    val type: String,
)