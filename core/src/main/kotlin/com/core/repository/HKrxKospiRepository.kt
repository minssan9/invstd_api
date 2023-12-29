package com.core.repository

import com.core.entity.HKrxKospi
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.LocalDate


interface HKrxKospiRepository : JpaRepository<HKrxKospi, Long> {
    fun findByIndexDate(indexDate: LocalDate): HKrxKospi
}
