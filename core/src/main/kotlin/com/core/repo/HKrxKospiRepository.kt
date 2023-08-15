package com.core.repo

import com.core.entity.HKrxKospi
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface HKrxKospiRepository : ReactiveCrudRepository<HKrxKospi, Long> {
    fun findByIndexDate(indexDate: LocalDate): Mono<HKrxKospi>
}
