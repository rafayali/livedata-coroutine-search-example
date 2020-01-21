package com.rafay.livedatacoroutinesearch.models

data class Users(val results: List<User>)

data class User(val gender: String, val name: Name)

data class Name(val title: String, val first: String, val last: String)