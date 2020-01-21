package com.rafay.livedatacoroutinesearch.api

import com.rafay.livedatacoroutinesearch.models.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api/1.0/?results=100")
    suspend fun getUser(@Query("seed") seed: String): Response<Users>
}