package com.ngengeapps.models

import retrofit2.Response

fun <T> Response<List<T>>.bodyList(): List<T> {
    return body() ?: listOf()
}