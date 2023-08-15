package com.core.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("h_krx_kospi")
data class HKrxKospi(
    @Id val id: Int,
    val indexDate: LocalDateTime,
    val indexData: Double
)
