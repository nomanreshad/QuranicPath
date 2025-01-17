package com.example.quranicpath.feature_quran.presentation.search

import com.example.quranicpath.feature_quran.domain.model.Surah

sealed interface SearchAction {
    data class OnSearchQueryChange(val query: String) : SearchAction
    data object OnCancelClick : SearchAction
    data class OnSurahClick(val surah: Surah) : SearchAction
    data class OnAyahClick(val ayahNumber: Int) : SearchAction
    data class OnTabSelected(val index: Int) : SearchAction
}