package com.kumar.androidtesting.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kumar.androidtesting.data.model.JobOpening
import com.kumar.androidtesting.domain.repository.JobSearchRepository
import com.kumar.androidtesting.util.MainCoroutineRule
import com.kumar.androidtesting.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDate
import java.time.Month

@OptIn(ExperimentalCoroutinesApi::class)
class JobSearchViewModelTest {

    @Mock
    lateinit var jobSearchRepository: JobSearchRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun fetchJobOpeningsFromServer_whenJobOpeningsAreFetchedSuccessfully_viewModelShouldUpdateJobOpenings() =
        runTest {
            // Given
            val jobOpening = JobOpening(
                title = "Android Engineer",
                company = "Google",
                postedDate = LocalDate.of(2024, Month.APRIL, 13),
                hasApplied = false
            )
            Mockito.`when`(jobSearchRepository.getJobOpenings()).thenReturn(
                listOf(
                    jobOpening
                )
            )

            // When
            val viewModel = JobSearchViewModel(jobSearchRepository)
            advanceUntilIdle() // Advances the testScheduler to the point where there are no tasks remaining

            val firstJobOpening = viewModel.jobOpenings.getOrAwaitValue().first()

            // Then
            assertEquals(jobOpening, firstJobOpening)
        }

    @Test
    fun fetchMoreJobOpeningsFromServer_whenMoreJobOpeningsAreFetchedSuccessfully_NewJobOpeningShouldBeAddedToExistingJobOpenings() =
        runTest {
            // Given
            val jobOpening = JobOpening(
                title = "Android Engineer",
                company = "Google",
                postedDate = LocalDate.of(2024, Month.APRIL, 13),
                hasApplied = false
            )
            Mockito.`when`(jobSearchRepository.getJobOpenings()).thenReturn(
                listOf(
                    jobOpening
                )
            )
            val anotherJobOpening = JobOpening(
                title = "Android Engineer 2",
                company = "Google",
                postedDate = LocalDate.of(2024, Month.APRIL, 13),
                hasApplied = false
            )
            Mockito.`when`(jobSearchRepository.getMoreJobOpenings()).thenReturn(
                listOf(
                    anotherJobOpening
                )
            )

            // When
            val viewModel = JobSearchViewModel(jobSearchRepository)
            viewModel.fetchMoreJobOpeningsFromServer()
            advanceUntilIdle()

            val jobOpenings = viewModel.jobOpenings.getOrAwaitValue()

            // Then
            assertEquals(jobOpening, jobOpenings.first())
            assertEquals(anotherJobOpening, jobOpenings[1])
        }
}