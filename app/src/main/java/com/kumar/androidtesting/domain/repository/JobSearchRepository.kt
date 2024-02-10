package com.kumar.androidtesting.domain.repository

import com.kumar.androidtesting.data.model.JobOpening
import java.util.concurrent.Flow

interface JobSearchRepository {

    suspend fun getJobOpenings(): List<JobOpening>

    suspend fun getMoreJobOpenings(): List<JobOpening>
}