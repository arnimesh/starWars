package com.example.myapplication232

import androidx.paging.PagingData
import com.kanyideveloper.starwars.models.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.Flow

interface ApiService {
    @GET("people")
    suspend fun getCharacters(): PeopleResponse

}