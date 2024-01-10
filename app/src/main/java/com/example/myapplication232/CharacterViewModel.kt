package com.example.myapplication232

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CharacterItemUiState(
    val name: String? = null,
    val birthYear: String? = null,
    val eyeColor: String? = null,
    val films: List<String> = listOf(),
    val gender: String? = null,

) {
    companion object {
        fun mapDomainCountryToUi(character: Character): CharacterItemUiState {
            return CharacterItemUiState(
                name = character.name,
                birthYear = character.birthYear,
                eyeColor = character.eyeColor,
                films = character.films,
                gender = character.gender,
            )
        }
    }
}

data class CharacterFilter(
    val searchQuery: String? = null,
)
    @HiltViewModel
    class CharacterViewModel @Inject constructor(
        characterRepository: CharacterRepository
    ) : ViewModel() {
    data class UiState(
        val filter: CharacterFilter = CharacterFilter()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val countriesFlow: Flow<PagingData<CharacterItemUiState>> = uiState.flatMapLatest {
        flowOf(it.filter)
    }.distinctUntilChanged()
        .flatMapLatest { filter ->
            characterRepository.getCharacters(filter.searchQuery).map { pagingData ->
                pagingData.map { CharacterItemUiState.mapDomainCountryToUi(it) }
            }
        }.cachedIn(viewModelScope)

    fun search(searchQuery: String) {
        _uiState.update {
            it.copy(filter = it.filter.copy(searchQuery = searchQuery))
        }
    }

}