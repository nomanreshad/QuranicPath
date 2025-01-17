package com.example.quranicpath.feature_quran.presentation.search

import com.example.quranicpath.core.presentation.UiText
import com.example.quranicpath.feature_quran.domain.model.AyahData
import com.example.quranicpath.feature_quran.domain.model.Surah

data class SearchState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val surah: Surah? = null,
    val favoriteSurahs: List<Surah> = emptyList(),
    val ayahData: AyahData? = null,
    val errorMessage: UiText? = null,
    val selectedTabIndex: Int = 0
)