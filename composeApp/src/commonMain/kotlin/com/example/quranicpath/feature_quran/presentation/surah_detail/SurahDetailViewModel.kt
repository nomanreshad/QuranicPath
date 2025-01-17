package com.example.quranicpath.feature_quran.presentation.surah_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.quranicpath.app.Route
import com.example.quranicpath.core.domain.onSuccess
import com.example.quranicpath.feature_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SurahDetailViewModel(
    private val quranRepository: QuranRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val surahNumber = savedStateHandle.toRoute<Route.SurahDetail>().surahNumber

    private val _state = MutableStateFlow(SurahDetailState())
    val state = _state
        .onStart {
            fetchSurahDetails()
            observeFavoriteSurahsStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _state.value
        )

    fun onAction(action: SurahDetailAction) {
        when(action) {
            is SurahDetailAction.OnSelectedSurahChange -> {
                _state.update { it.copy(
                    surah = action.surah
                ) }
            }
            SurahDetailAction.OnFavoriteClick -> {
                viewModelScope.launch {
                    if (state.value.isFavorite) {
                        quranRepository.deleteFromFavorites(surahNumber)
                    } else {
                        state.value.surah?.let { surah ->
                            quranRepository.markAsFavorite(surah)
                        }
                    }
                }
            }
            else -> Unit
        }
    }

    private fun observeFavoriteSurahsStatus() {
        quranRepository
            .isSurahFavorite(surahNumber)
            .onEach { isFavorite ->
                _state.update { it.copy(
                    isFavorite = isFavorite
                ) }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchSurahDetails() {
        viewModelScope.launch {
            quranRepository.getSurahDetails(surahNumber)
                .onSuccess { ayahs ->
                    _state.update { it.copy(
                        isLoading = false,
                        surah = it.surah?.copy(ayahs = ayahs),
                    ) }
                }
        }
    }

//    private fun getSurahDetails(surahNumber: Int) {
//        viewModelScope.launch {
//            _state.update { it.copy(
//                isLoading = true
//            ) }
//            quranRepository.getSurahDetails(surahNumber)
//                .onSuccess { surahData ->
//                    _state.update { it.copy(
//                        isLoading = false,
//                        surahData = surahData,
//                        errorMessage = null
//                    ) }
//                }
//                .onError { error ->
//                    _state.update { it.copy(
//                        isLoading = false,
//                        surahData = null,
//                        errorMessage = error.toUiText()
//                    ) }
//                }
//        }
//    }
}