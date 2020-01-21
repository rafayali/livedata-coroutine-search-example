package com.rafay.livedatacoroutinesearch.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rafay.livedatacoroutinesearch.api.RetrofitApi
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MethodTwoViewModel : ViewModel() {

    private val randomUserApi = RetrofitApi().randomUsers

    private val _query = MutableLiveData<String>()

    val results = liveData {
        var job: Job? = null

        val queryObserver = Observer<String> {
            job?.cancel()
            job = GlobalScope.launch {
                if (it.isNotBlank()) {
                    val response = randomUserApi.getUser(it)
                    if (response.code() == 200) {
                        val users =
                            response.body()?.results?.map { "${it.name.first} ${it.name.last}" }
                                ?: listOf()
                        emit(users)
                    }
                } else {
                    emit(listOf())
                }
            }
        }

        _query.observeForever(queryObserver)

        try {
            CompletableDeferred<Unit>().await()
        } finally {
            job?.cancel()
            _query.removeObserver(queryObserver)
        }
    }

    fun search(query: String) {
        _query.postValue(query)
    }
}