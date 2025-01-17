package com.example.quranicpath.feature_quran.presentation.surah_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranicpath.core.domain.onError
import com.example.quranicpath.core.domain.onSuccess
import com.example.quranicpath.core.presentation.toUiText
import com.example.quranicpath.feature_quran.domain.repository.QuranRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SurahListViewModel(
    private val quranRepository: QuranRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SurahListState())
    val state = _state
        .onStart {
            getSurahs()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _state.value
        )

    private fun getSurahs() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            quranRepository.getSurahs()
                .onSuccess { surahs ->
                    _state.update { it.copy(
                        isLoading = false,
                        surahs = surahs,
                        errorMessage = null
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(
                        isLoading = false,
                        surahs = emptyList(),
                        errorMessage = error.toUiText()
                    ) }
                }
        }
    }
}