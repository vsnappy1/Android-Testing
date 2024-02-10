package com.kumar.androidtesting.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumar.androidtesting.data.model.JobOpening
import com.kumar.androidtesting.data.repository.JobSearchRepositoryImpl
import com.kumar.androidtesting.domain.repository.JobSearchRepository
import kotlinx.coroutines.launch

class JobSearchViewModel(
    private val repository: JobSearchRepository = JobSearchRepositoryImpl()
) : ViewModel() {

    private val _jobOpenings: MutableLiveData<List<JobOpening>> = MutableLiveData()
    val jobOpenings: LiveData<List<JobOpening>> = _jobOpenings

    init {
        fetchJobOpeningsFromServer()
    }

    private fun fetchJobOpeningsFromServer() {
        viewModelScope.launch {
            _jobOpenings.postValue(repository.getJobOpenings())
        }
    }

    fun fetchMoreJobOpeningsFromServer() {
        viewModelScope.launch {
            _jobOpenings.postValue(_jobOpenings.value?.plus(repository.getMoreJobOpenings()))
        }
    }
}