package com.kumar.androidtesting.data.repository

import com.kumar.androidtesting.data.model.JobOpening
import com.kumar.androidtesting.domain.repository.JobSearchRepository
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.Month

class JobSearchRepositoryImpl : JobSearchRepository {
    override suspend fun getJobOpenings(): List<JobOpening> {
        // Fake the network request
        delay(1000)
        return listOf(
            JobOpening(
                title = "Senior Software Engineer",
                company = "Apple",
                postedDate = LocalDate.of(2024, Month.APRIL, 13),
                hasApplied = false
            )
        )
    }

    override suspend fun getMoreJobOpenings(): List<JobOpening> {
        delay(1000)
        return listOf(
            JobOpening(
                title = "Software Engineer",
                company = "Google",
                postedDate = LocalDate.of(2024, Month.APRIL, 13),
                hasApplied = false
            )
        )
    }
}