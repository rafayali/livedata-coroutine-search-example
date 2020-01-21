package com.rafay.livedatacoroutinesearch.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rafay.livedatacoroutinesearch.api.RetrofitApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MethodOneViewModel : ViewModel() {

    private val randomUserApi = RetrofitApi().randomUsers

    private val _query = MutableLiveData<String>()

    private var job = Job()

    val results = Transformations.switchMap(_query) {
        job.cancel()
        job = Job()
        liveData(CoroutineScope(job + Dispatchers.IO).coroutineContext) {
            if (it.isNotBlank()) {
                val response = randomUserApi.getUser(it)
                if (response.code() == 200) {
                    val users = response.body()?.results?.map { "${it.name.first} ${it.name.last}" }
                        ?: listOf()
                    emit(users)
                }
            } else {
                emit(listOf())
            }
        }
    }

    fun search(query: String) {
        _query.postValue(query)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}