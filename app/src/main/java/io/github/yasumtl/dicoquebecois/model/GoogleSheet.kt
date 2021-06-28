package io.github.yasumtl.dicoquebecois.model

import retrofit2.Call
import retrofit2.http.GET

interface GoogleSheet {
    @GET("values?alt=json")
    fun getAllExpressions(): Call<Expression>
}