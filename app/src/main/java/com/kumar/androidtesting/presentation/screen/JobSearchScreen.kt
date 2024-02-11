package com.kumar.androidtesting.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kumar.androidtesting.data.model.JobOpening
import com.kumar.androidtesting.presentation.viewmodel.JobSearchViewModel
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

@Composable
fun JobSearchScreen(
    viewModel: JobSearchViewModel = viewModel()
) {
    val jobOpenings by viewModel.jobOpenings.observeAsState(emptyList())
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Job Openings", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(jobOpenings) {
                JobOpeningCard(jobOpening = it)
            }
        }
    }
}

@Composable
private fun JobOpeningCard(jobOpening: JobOpening) {
    Card(modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Job opening card" }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = jobOpening.title, style = MaterialTheme.typography.titleLarge)
            Text(text = jobOpening.company, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = jobOpening.postedDate.format(DateTimeFormatter.ISO_DATE),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun PreviewJobSearchCard() {
    JobOpeningCard(
        JobOpening(
            title = "Android Engineer",
            company = "Google",
            postedDate = LocalDate.of(2024, Month.APRIL, 13),
            hasApplied = false
        )
    )
}