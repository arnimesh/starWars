package com.example.myapplication232

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharacterRepository {

    //    override fun getEuropeanCountries(): Flow<PagingData<Character>> {
//        val config = PagingConfig(
//            pageSize = NETWORK_PAGE_SIZE,
//            enablePlaceholders = false,
//            initialLoadSize = 10,
//        )
//        return Pager(
//            config = config,
//            pagingSourceFactory = { CountriesPagingSource(countriesService) }
//        ).flow
//    }
    override fun getCharacters(searchQuery: String?): Flow<PagingData<Character>> {
        val config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = 10,
        )
        return Pager(
            config = config,
            pagingSourceFactory = { CharactersPagingSource(apiService,searchQuery) }
        ).flow
    }

}