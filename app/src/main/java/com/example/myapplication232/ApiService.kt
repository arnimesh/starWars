package com.example.myapplication232

import com.kanyideveloper.starwars.models.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("people/?page/")
    suspend fun getCharacters(@Query("page") page: Int): PeopleResponse

}