@file:OptIn(FlowPreview::class)

package com.example.quranicpath.feature_quran.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranicpath.core.domain.onError
import com.example.quranicpath.core.domain.onSuccess
import com.example.quranicpath.core.presentation.UiText
import com.example.quranicpath.core.presentation.toUiText
import com.example.quranicpath.feature_quran.domain.repository.QuranRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import quranicpath.composeapp.generated.resources.Res
import quranicpath.composeapp.generated.resources.search_with_number

class SearchViewModel(
    private val quranRepository: QuranRepository
) : ViewModel(){

    private var searchSurahJob: Job? = null
    private var searchAyahJob: Job? = null
    private var favoriteSurahJob: Job? = null

    private val _state = MutableStateFlow(SearchState())
    val state = _state
        .onStart {
            observeSearchQuery()
            observeFavoriteSurahs()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: SearchAction) {
        when(action) {
            is SearchAction.OnSearchQueryChange -> {
                _state.update { it.copy(
                    searchQuery = action.query
                ) }
            }

            is SearchAction.OnTabSelected -> {
                _state.update { it.copy(
                    selectedTabIndex = action.index
                ) }
            }

            else -> Unit
        }
    }

    private fun observeFavoriteSurahs() {
        favoriteSurahJob?.cancel()
        favoriteSurahJob = quranRepository
            .getFavoriteSurahs()
            .onEach { favoriteSurahs ->
                _state.update { it.copy(
                    favoriteSurahs = favoriteSurahs
                ) }
            }
            .launchIn(viewModelScope)
    }

    private fun observeSearchQuery() {
        state.map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update { it.copy(
                            errorMessage = UiText.DynamicString("Your search results will show here")
                        ) }
                    }

                     query.isNotBlank() -> {
                         if (query.toIntOrNull() == null) {
                             _state.update { it.copy(
                                 errorMessage = UiText.StringResourceId(Res.string.search_with_number)
                             ) }
                             return@onEach
                         }
                         searchSurahJob?.cancel()
                         searchAyahJob?.cancel()
                         searchSurahJob = searchSurah(query.toInt())
                         searchAyahJob = searchAyah(query.toInt())
                     }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchSurah(surahNumber: Int) = viewModelScope.launch {
        _state.update { it.copy(
            isLoading = true
        ) }
        quranRepository.getSurahs()
            .onSuccess { surahs ->
                val surah = surahs.find { it.surahNumber == surahNumber }
                _state.update { it.copy(
                    isLoading = false,
                    surah = surah,
                    errorMessage = null
                ) }
            }
            .onError { error ->
                _state.update { it.copy(
                    isLoading = false,
                    surah = null,
                    errorMessage = error.toUiText()
                ) }
            }
    }

    private fun searchAyah(ayahNumber: Int) = viewModelScope.launch {
        _state.update { it.copy(
            isLoading = true
        ) }
        quranRepository.getAyah(ayahNumber)
            .onSuccess { ayahData ->
                _state.update { it.copy(
                    isLoading = false,
                    ayahData = ayahData,
                    errorMessage = null
                ) }
            }
            .onError { error ->
                _state.update { it.copy(
                    isLoading = false,
                    ayahData = null,
                    errorMessage = error.toUiText()
                ) }
            }
    }
}