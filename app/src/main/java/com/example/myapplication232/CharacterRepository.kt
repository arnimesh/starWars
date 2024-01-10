package com.example.myapplication232

import androidx.paging.PagingData
import com.kanyideveloper.starwars.models.PeopleResponse
import java.util.concurrent.Flow

interface CharacterRepository {
    fun getCharacters(): kotlinx.coroutines.flow.Flow<PagingData<Character>>

}