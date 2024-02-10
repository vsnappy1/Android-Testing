package com.kumar.androidtesting.data.model

import java.time.LocalDate
import java.util.Date

data class JobOpening(
    val title: String,
    val company: String,
    val postedDate: LocalDate,
    val hasApplied: Boolean
)
