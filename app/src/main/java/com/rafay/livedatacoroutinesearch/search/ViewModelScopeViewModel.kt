package com.rafay.livedatacoroutinesearch.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafay.livedatacoroutinesearch.api.RetrofitApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewModelScopeViewModel : ViewModel() {

    private val randomUserApi = RetrofitApi().randomUsers

    private var job: Job? = null

    private val _results = MutableLiveData<List<String>>()
    val results: LiveData<List<String>> = _results

    fun search(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            if (query.isNotBlank()) {
                val response = randomUserApi.getUser(query)
                if (response.code() == 200) {
                    val users = response.body()?.results?.map { "${it.name.first} ${it.name.last}" }
                        ?: listOf()
                    _results.postValue(users)
                }
            } else {
                _results.postValue(listOf())
            }
        }
    }
}