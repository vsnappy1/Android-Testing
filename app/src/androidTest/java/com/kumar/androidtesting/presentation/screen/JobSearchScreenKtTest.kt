package com.kumar.androidtesting.presentation.screen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import com.kumar.androidtesting.data.model.JobOpening
import com.kumar.androidtesting.domain.repository.JobSearchRepository
import com.kumar.androidtesting.presentation.viewmodel.JobSearchViewModel
import com.kumar.androidtesting.ui.theme.AndroidTestingTheme
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate
import java.time.Month

class JobSearchScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun whenJobOpeningsAreFetched_theseShouldBeVisibleOnScreen() = runTest {
        // Given
        val jobSearchRepository = mock<JobSearchRepository>()
        Mockito.`when`(jobSearchRepository.getJobOpenings()).thenReturn(
            listOf(
                JobOpening(
                    title = "Android Engineer",
                    company = "Google",
                    postedDate = LocalDate.of(2024, Month.APRIL, 13),
                    hasApplied = false
                )
            )
        )

        // When
        composeTestRule.setContent {
            AndroidTestingTheme {
                JobSearchScreen(JobSearchViewModel(jobSearchRepository))
            }
        }

        // Then
        composeTestRule.onNodeWithContentDescription(label = "Job opening card").assertExists()
    }
}